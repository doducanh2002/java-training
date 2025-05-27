package org.aibles.feature.booking.business.usecase.impl;

import java.time.LocalDateTime;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.bussiness.dto.CustomerMilesAvailableProjectionUseCase;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.exception.DisqualifiedCustomerException;
import org.aibles.feature.booking.business.exception.GiveAwayDayExpiredPromotionException;
import org.aibles.feature.booking.business.exception.PromotionNotFoundException;
import org.aibles.feature.booking.business.usecase.RegisterPromotionUseCase;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.framework.repository.projection.RegisterPromotionProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterPromotionUseCaseImpl implements RegisterPromotionUseCase {

  private static final Logger log = LoggerFactory.getLogger(RegisterPromotionUseCaseImpl.class);

  private final PromotionRepository promotionRepository;
  private final CustomerRepository customerRepository;
  private final CustomerPromotionRepository customerPromotionRepository;

  public RegisterPromotionUseCaseImpl(PromotionRepository promotionRepository,
      CustomerRepository customerRepository,
      CustomerPromotionRepository customerPromotionRepository) {
    this.promotionRepository = promotionRepository;
    this.customerRepository = customerRepository;
    this.customerPromotionRepository = customerPromotionRepository;
  }

  @Override
  public void invoke(String promotionId, String accountId) {
    RegisterPromotionProjection registerPromotionProjection = promotionRepository
        .findArchivedMilesAndGiveAwayDayById(promotionId)
        .orElseThrow(() -> {
          log.error("(invoke)promotionId: {}", promotionId);
          return new PromotionNotFoundException(promotionId);
        });

    if (registerPromotionProjection.getGiveAwayDay().isBefore(LocalDateTime.now())) {
      log.error("(invoke)Give away day expired promotion exception!!");
      throw new GiveAwayDayExpiredPromotionException(promotionId);
    }

    CustomerMilesAvailableProjectionUseCase projectionUseCase = customerRepository.findIdAndMilesArchivedByAccountId(
        accountId);
    if (projectionUseCase.getMilesAvailable() == null) {
      projectionUseCase.setMilesAvailable(0L);
    }

    if (projectionUseCase.getMilesAvailable() < registerPromotionProjection.getArchivedMiles()) {
      log.error("(invoke)dis qualified customer exception!!");
      throw new DisqualifiedCustomerException();
    }

    CustomerPromotion customerPromotion = new CustomerPromotion();
    Customer customer = new Customer();
    customer.setId(projectionUseCase.getId());
    customerPromotion.setCustomer(customer);
    Promotion promotion = new Promotion();
    promotion.setId(promotionId);
    customerPromotion.setPromotion(promotion);
    customerPromotion.setWinner(false);
    customerPromotion.setIsUsed(false);

    customerPromotionRepository.registerPromotion(customerPromotion);
  }
}
