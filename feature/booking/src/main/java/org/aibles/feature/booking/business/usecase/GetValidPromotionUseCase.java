package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.dto.response.PromotionResponse;

public interface GetValidPromotionUseCase {

  PagingResponse<PromotionResponse> invoke(PagingRequest request, String userId);
}
