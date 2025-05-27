package org.aibles.feature.booking.business.usecase.impl;

import java.util.Optional;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.bussiness.dto.CustomerMilesAvailableProjectionUseCase;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.response.PromotionDetailResponse;
import org.aibles.feature.booking.business.exception.PromotionNotFoundException;
import org.aibles.feature.booking.business.usecase.GetDetailPromotionUseCase;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetDetailPromotionUseCaseImpl implements GetDetailPromotionUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      GetDetailPromotionUseCaseImpl.class);

  private final PromotionRepository promotionRepository;
  private final CustomerRepository customerRepository;
  private final CustomerPromotionRepository customerPromotionRepository;

  public GetDetailPromotionUseCaseImpl(PromotionRepository promotionRepository,
      CustomerRepository customerRepository,
      CustomerPromotionRepository customerPromotionRepository) {
    this.promotionRepository = promotionRepository;
    this.customerRepository = customerRepository;
    this.customerPromotionRepository = customerPromotionRepository;
  }

  @Override
  public PromotionDetailResponse invoke(String id, String accountId) {
    Promotion promotion = promotionRepository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(invoke)id: {} not found", id);
          throw new PromotionNotFoundException(id);
        });

    CustomerMilesAvailableProjectionUseCase projectionUseCase = customerRepository.findIdAndMilesArchivedByAccountId(
        accountId);
    if (projectionUseCase.getMilesAvailable() == null) {
      projectionUseCase.setMilesAvailable(0L);
    }

    Optional<CustomerPromotion> customerPromotion = customerPromotionRepository.findByCustomerIdAndPromotionId(projectionUseCase.getId(), id);
    PromotionDetailResponse response = new PromotionDetailResponse();
    response.setId(promotion.getId());
    response.setCode(promotion.getCode());
    response.setDescription(promotion.getDescription());
    response.setExpiredDate(promotion.getExpiredDate());
    response.setGiveAwayDay(promotion.getGiveAwayDay());
    response.setDiscountPercent(promotion.getDiscountPercent());
    response.setArchivedMiles(promotion.getArchivedMiles());
    response.setPlusMiles(promotion.getPlusMiles());
    response.setQuantity(promotion.getQuantity());

    if (customerPromotion.isEmpty()) {
      response.setQualified(false);
      response.setWinner(false);
      response.setUsed(false);
      return response;
    } else {
      boolean isQualified = projectionUseCase.getMilesAvailable() >= promotion.getArchivedMiles();
      response.setQualified(isQualified);
      response.setWinner(customerPromotion.map(CustomerPromotion::getWinner).orElse(false));
      response.setUsed(customerPromotion.map(CustomerPromotion::getIsUsed).orElse(false));
      return response;
    }
  }
}
