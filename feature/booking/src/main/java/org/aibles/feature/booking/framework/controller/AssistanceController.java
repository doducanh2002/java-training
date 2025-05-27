package org.aibles.feature.booking.framework.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.response.SpecialAssistanceResponse;
import org.aibles.feature.booking.business.usecase.GetSpecialAssistanceUsecase;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/special-assistances")
public class AssistanceController {
  private final GetSpecialAssistanceUsecase getSpecialAssistanceUsecase;

  @GetMapping
  public BaseResponse<List<SpecialAssistanceResponse>> getSpecialAssistances() {
    log.info("getSpecialAssistances()");
    return new BaseResponse<>(
        HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        getSpecialAssistanceUsecase.invoke());
  }
}
