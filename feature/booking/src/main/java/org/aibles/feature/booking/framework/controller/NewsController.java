package org.aibles.feature.booking.framework.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.NewsResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.usecase.GetNewsUsecase;
import org.aibles.feature.booking.framework.controller.dto.request.PagingReq;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

  private final GetNewsUsecase getNewsUsecase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PagingResponse<NewsResponse>> getNewsList(
      @Valid PagingReq pagingReq,
      @RequestParam(required = false, defaultValue = "true") Boolean status,
      @RequestParam(required = false) String[] sortBy) {
    log.info("getNewsList: status: {}, sortBy: {}", status, sortBy);
    PagingRequest pagingRequest =
        new PagingRequest(pagingReq.getNum(), pagingReq.getPage(), sortBy);
    return new BaseResponse<>(
        HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        getNewsUsecase.invoke(pagingRequest, status));
  }
}
