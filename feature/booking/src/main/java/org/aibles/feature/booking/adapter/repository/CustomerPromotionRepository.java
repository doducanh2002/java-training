package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.CustomerPromotion;

public interface CustomerPromotionRepository {

  void registerPromotion(CustomerPromotion customerPromotion);

  Optional<CustomerPromotion> findByCustomerIdAndPromotionId(String customerId, String promotionId);

  Optional<CustomerPromotion> findByPromotionId(String promotionId);

   List<CustomerPromotion> findAllCustomerByPromotionIdAndWinnerStatus(String promotionId, boolean winnerStatus);

   void saveListCustomerPromotion(List<CustomerPromotion> listCustomerPromotion);
}
