package org.aibles.feature.booking.framework.controller;

import static org.aibles.util.SecurityUtil.getCredential;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.response.TripResponse;
import org.aibles.feature.booking.business.usecase.GetBookedTripsUseCase;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerBookingController {

  private final GetBookedTripsUseCase getBookedTripsUseCase;

  @GetMapping("/self/trips")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<TripResponse>> getBookedTrips() {
    log.info("(getBookedTrips)");
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getBookedTripsUseCase.invoke(getCredential()));
  }
}
