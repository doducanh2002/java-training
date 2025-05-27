package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.GetAllPromotionResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;

public interface GetAllPromotionUseCase {
    PagingResponse<GetAllPromotionResponse> invoke(PagingRequest request);
}
