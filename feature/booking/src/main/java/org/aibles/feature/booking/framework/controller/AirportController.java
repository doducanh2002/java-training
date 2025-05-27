package org.aibles.feature.booking.framework.controller;

import java.io.IOException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.request.UpdateAirportUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.AirportResponse;
import org.aibles.feature.booking.business.usecase.GetListAirportUsecase;
import org.aibles.feature.booking.business.usecase.ImportAirportUseCase;
import org.aibles.feature.booking.business.usecase.UpdateAirportUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.UpdateAirportRequest;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
@Slf4j
public class AirportController {
  private final GetListAirportUsecase getListAirportUsecase;
  private final ImportAirportUseCase importAirportUseCase;
  private final UpdateAirportUseCase updateAirportUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<AirportResponse>> getAirports(
      @RequestParam String country, @RequestParam(required = false) String region) {
    log.info("getAirports: country: {}, region: {}", country, region);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), getListAirportUsecase.invoke(country, region));
  }

  @PostMapping("/import")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> importAirport(@RequestBody MultipartFile file) throws IOException {
    importAirportUseCase.invoke(file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @PutMapping("/{airport_id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<AirportResponse> updateAirports(@PathVariable(value = "airport_id")String airportId,
  @Valid@RequestBody UpdateAirportRequest request) {
    UpdateAirportUseCaseRequest useCaseRequest = UpdateAirportUseCaseRequest
        .builder()
        .name(request.getName())
        .code(request.getCode())
        .country(request.getCountry())
        .region(request.getRegion())
        .build();
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), updateAirportUseCase.invoke(airportId, useCaseRequest));
  }
}
