package org.aibles.feature.booking.framework.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.response.TravelClassResponse;
import org.aibles.feature.booking.business.usecase.GetListTravelClassUseCase;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/travel-classes")
@RequiredArgsConstructor
@Slf4j
public class TravelClassController {
  private final GetListTravelClassUseCase getListTravelClassUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<TravelClassResponse>> getTravelClass() {
    log.info("getTravelClass");
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), getListTravelClassUseCase.invoke());
  }
}
