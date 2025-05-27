package org.aibles.feature.booking.framework.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.response.TripResponse;
import org.aibles.feature.booking.business.dto.response.ValidateSeatResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;
import org.aibles.feature.booking.business.usecase.GetTripCheckInUseCase;
import org.aibles.feature.booking.business.usecase.ImportTripUseCase;
import org.aibles.feature.booking.business.usecase.ValidateSeatUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.FilterTripsRequest;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.aibles.feature.booking.framework.impl.FilterTripsFactory;
import org.aibles.feature.booking.framework.validation.ValidateConfirmationCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@Slf4j
public class TripController {

  private final GetTripCheckInUseCase getTripCheckInUseCase;

  private final FilterTripsFactory filterTripsFactory;
  private final ImportTripUseCase importTripUseCase;
  private final ValidateSeatUseCase validateSeatUseCase;

  @GetMapping("/check-in")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<TripResponse>> getTripCheckIn(
      @RequestParam String passengerLastName,
      @ValidateConfirmationCode @RequestParam String confirmationCode) {
    log.info("(getTripCheckIn)passengerLastName: {}, confirmationCode: {}", passengerLastName, confirmationCode);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getTripCheckInUseCase.invoke(passengerLastName, confirmationCode));
  }

  @PostMapping("/filter")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<FilterTripsResponse>> getFilteredTrip(
      @RequestBody @Valid FilterTripsRequest request
  ) {
    log.info("(getFilteredTrip)request: {}", request);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        filterTripsFactory.invoke(request));
  }

  @PostMapping("/import")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> importTrip(@RequestBody MultipartFile file) throws IOException {
    importTripUseCase.invoke(file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @PostMapping("/{tripId}/fares/{fareId}/availability")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<ValidateSeatResponse> validateSeat(@PathVariable(value = "tripId") String tripId, @PathVariable(value = "fareId") String fareId) {
    log.info("(validateSeat)tripId: {}, fareId: {}", tripId, fareId);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        validateSeatUseCase.invoke(tripId, fareId));
  }
}
