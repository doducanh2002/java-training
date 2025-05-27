package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.response.ValidatePromotionCodeResponse;
import org.aibles.feature.booking.business.exception.CustomerNotFoundException;
import org.aibles.feature.booking.business.exception.PromotionAlreadyUsedException;
import org.aibles.feature.booking.business.exception.PromotionCodeNotEligibleBadRequest;
import org.aibles.feature.booking.business.exception.PromotionCodeNotFoundException;
import org.aibles.feature.booking.business.usecase.ValidatePromotionUseCase;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.framework.datasource.CustomerDatabaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatePromotionUseCaseImpl implements ValidatePromotionUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      ValidatePromotionUseCaseImpl.class);

  private final PromotionRepository promotionRepository;
  private final CustomerPromotionRepository customerPromotionRepository;
  private final CustomerRepository customerRepository;

  public ValidatePromotionUseCaseImpl(PromotionRepository promotionRepository, CustomerPromotionRepository customerPromotionRepository,
      CustomerRepository customerRepository) {
    this.promotionRepository = promotionRepository;
    this.customerPromotionRepository = customerPromotionRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public ValidatePromotionCodeResponse invoke(String accountId, String code) {
    Customer customer = customerRepository.findCustomerByAccountId(accountId)
        .orElseThrow(() ->  new CustomerNotFoundException(accountId));;

    Promotion promotion = promotionRepository.findByCode(code)
        .orElseThrow(() -> new PromotionCodeNotFoundException(code));

    CustomerPromotion customerPromotion = customerPromotionRepository
        .findByCustomerIdAndPromotionId(customer.getId(), promotion.getId())
        .orElseThrow(() -> new PromotionCodeNotEligibleBadRequest(customer.getId(), code));
    return new ValidatePromotionCodeResponse(customerPromotion.getIsUsed());
  }
}