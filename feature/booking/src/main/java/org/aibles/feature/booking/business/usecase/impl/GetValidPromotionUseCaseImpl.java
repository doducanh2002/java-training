package org.aibles.feature.booking.business.usecase.impl;

import java.util.List;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.dto.response.PromotionResponse;
import org.aibles.feature.booking.business.exception.CustomerNotFoundException;
import org.aibles.feature.booking.business.usecase.GetValidPromotionUseCase;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetValidPromotionUseCaseImpl implements GetValidPromotionUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      GetValidPromotionUseCaseImpl.class);

  private final PromotionRepository promotionRepository;
  private final CustomerRepository customerRepository;
  private final CustomerPromotionRepository customerPromotionRepository;


  public GetValidPromotionUseCaseImpl(PromotionRepository promotionRepository, CustomerRepository customerRepository,
      CustomerPromotionRepository customerPromotionRepository) {
    this.promotionRepository = promotionRepository;
    this.customerRepository = customerRepository;
    this.customerPromotionRepository = customerPromotionRepository;
  }

  @Override
  public PagingResponse<PromotionResponse> invoke(PagingRequest request, String userId) {
    PagingResponse<Promotion> promotionPagingResponse = promotionRepository.getValidPromotion(request);
    Customer customer=  customerRepository.findCustomerByAccountId(userId)
        .orElseThrow(()-> new CustomerNotFoundException(userId));
    List<PromotionResponse> promotionResponses = promotionPagingResponse.getPageData().stream().map(promotion -> {
      CustomerPromotion customerPromotion = customerPromotionRepository
          .findByCustomerIdAndPromotionId(customer.getId(), promotion.getId())
          .orElse(null);
   PromotionResponse response = PromotionResponse.from(promotion, customerPromotion);
      if (customerPromotion == null) {
        response.setIsQualified(false);
        response.setIsWinner(false);
        response.setIsUsed(false);
      } else {
        boolean isQualified = customer.getMilesAvailable() >= promotion.getArchivedMiles();
        response.setIsQualified(isQualified);
        response.setIsWinner(Boolean.TRUE.equals(customerPromotion.getWinner()));
        response.setIsUsed(Boolean.TRUE.equals(customerPromotion.getIsUsed()));
      }
      return response;
    }).toList();

    return new PagingResponse<>(
        promotionPagingResponse.getNum() + 1,
        promotionPagingResponse.getSize(),
        promotionPagingResponse.getTotal(),
        promotionResponses
    );
  }
}
