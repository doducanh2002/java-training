package org.aibles.feature.booking.business.usecase.impl;

import java.util.List;
import org.aibles.feature.booking.adapter.repository.NewsRepository;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.NewsResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.usecase.GetNewsUsecase;
import org.aibles.feature.booking.entity.News;

public class GetNewsUsecaseImpl implements GetNewsUsecase {

  private final NewsRepository newsRepository;

  public GetNewsUsecaseImpl(NewsRepository newsRepository) {
    this.newsRepository = newsRepository;
  }

  @Override
  public PagingResponse<NewsResponse> invoke(PagingRequest pagingReq, Boolean status) {
    PagingResponse<News> newsPage = newsRepository.getNewsList(pagingReq, status);
    List<NewsResponse> newsResponses = newsPage.getPageData().stream().map(NewsResponse::from).toList();
    return new PagingResponse<>(newsPage.getNum(),
        newsPage.getSize(), newsPage.getTotal(), newsResponses);
  }
}
