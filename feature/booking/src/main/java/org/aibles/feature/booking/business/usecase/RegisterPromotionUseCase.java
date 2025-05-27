package org.aibles.feature.booking.business.usecase;

public interface RegisterPromotionUseCase {

  void invoke(String promotionId, String customerId);
}
