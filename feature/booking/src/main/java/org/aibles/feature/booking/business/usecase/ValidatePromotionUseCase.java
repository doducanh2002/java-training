package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.ValidatePromotionCodeResponse;

public interface ValidatePromotionUseCase {
  ValidatePromotionCodeResponse invoke(String customerId, String code);
}
