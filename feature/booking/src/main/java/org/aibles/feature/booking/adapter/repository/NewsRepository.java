package org.aibles.feature.booking.adapter.repository;

import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.News;

public interface NewsRepository {

  PagingResponse<News> getNewsList(PagingRequest request, Boolean status);
}
