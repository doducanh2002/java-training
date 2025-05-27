package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.NewsResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;

public interface GetNewsUsecase {

  PagingResponse<NewsResponse> invoke(PagingRequest pagingReq, Boolean status);

}
