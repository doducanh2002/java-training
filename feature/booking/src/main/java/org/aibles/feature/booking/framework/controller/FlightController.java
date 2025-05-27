package org.aibles.feature.booking.framework.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.request.ChangeFlightStatusUsecaseRequest;
import org.aibles.feature.booking.business.dto.response.AvailabilitySeatResponse;
import org.aibles.feature.booking.business.dto.response.FlightStatusResponse;
import org.aibles.feature.booking.business.dto.response.MealResponse;
import org.aibles.feature.booking.business.usecase.ChangeStatusFlightUseCase;
import org.aibles.feature.booking.business.usecase.CheckFlightSeatAvailabilityUseCase;
import org.aibles.feature.booking.business.usecase.GetFlightStatusUsecase;
import org.aibles.feature.booking.business.usecase.GetMealByFlightUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.AvailableSeatsRequest;
import org.aibles.feature.booking.framework.controller.dto.request.ChangeFlightStatusRequest;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@Slf4j
public class FlightController {

  private final GetFlightStatusUsecase getFlightStatusUsecase;
  private final GetMealByFlightUseCase getMealByFlightUseCase;
  private final CheckFlightSeatAvailabilityUseCase checkFlightSeatAvailabilityUseCase;
  private final ChangeStatusFlightUseCase changeStatusFlightUseCase;

  @GetMapping
  public BaseResponse<List<FlightStatusResponse>> getFlights(@RequestParam String from,
      @RequestParam String to,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getFlightStatusUsecase.invoke(from, to, date));
  }

  @GetMapping("/{flight_id}/meals")
  public BaseResponse<List<MealResponse>> getMealsByFlight(
      @PathVariable("flight_id") String flightId) {
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getMealByFlightUseCase.invoke(flightId));
  }

  @PostMapping("/{flight_id}/seats/availability")
  public BaseResponse<AvailabilitySeatResponse> validateAvailableSeat(
      @PathVariable("flight_id") String flightId, @RequestBody @Valid AvailableSeatsRequest request) {
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        checkFlightSeatAvailabilityUseCase.invoke(flightId, request.getSeats()));
  }

  @PatchMapping("/{flight_id}/status-change")
  public BaseResponse<Void> changeFlightStatus(
          @PathVariable("flight_id") String flightId,
          @RequestBody @Valid ChangeFlightStatusRequest request) {
    log.info("Request received to change status of flight ID {} to {}", flightId, request.getStatus());
    ChangeFlightStatusUsecaseRequest usecaseRequest =
            new ChangeFlightStatusUsecaseRequest(flightId, request.getStatus());

    changeStatusFlightUseCase.invoke(usecaseRequest);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}
