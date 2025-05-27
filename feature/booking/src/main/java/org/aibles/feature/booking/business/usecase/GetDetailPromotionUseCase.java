package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.PromotionDetailResponse;

public interface GetDetailPromotionUseCase {

  PromotionDetailResponse invoke(String id, String accountId);
}
