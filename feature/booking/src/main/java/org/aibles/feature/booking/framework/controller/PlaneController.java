package org.aibles.feature.booking.framework.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.request.CreatePlaneUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.UpdatePlaneUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.BookingStatusResponse;
import org.aibles.feature.booking.business.dto.response.PlaneResponse;
import org.aibles.feature.booking.business.dto.response.planedetail.PlaneDetailResponse;
import org.aibles.feature.booking.business.usecase.CreatePlaneUseCase;
import org.aibles.feature.booking.business.usecase.GetListPlaneUsecase;
import org.aibles.feature.booking.business.usecase.GetPlaneDetailUseCase;
import org.aibles.feature.booking.business.usecase.GetPlaneFareStatusUsecase;
import org.aibles.feature.booking.business.usecase.UpdatePlaneUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.CreatePlaneRequest;
import org.aibles.feature.booking.framework.controller.dto.request.GetPlaneFareStatusRequest;
import org.aibles.feature.booking.framework.controller.dto.request.UpdatePlaneRequest;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/planes")
@RequiredArgsConstructor
@Slf4j
public class PlaneController {

  private final GetListPlaneUsecase getListPlaneUsecase;
  private final CreatePlaneUseCase createPlaneUseCase;
  private final GetPlaneDetailUseCase getPlaneDetailUseCase;
  private final UpdatePlaneUseCase updatePlaneUseCase;
  private final GetPlaneFareStatusUsecase getPlaneFareStatusUsecase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<List<PlaneResponse>> getListPlane() {
    return new BaseResponse<>(
        HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), getListPlaneUsecase.invoke());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> createPlane(@Valid @RequestPart("templateRequest") CreatePlaneRequest request,
      @RequestBody MultipartFile file)
      throws IOException {
    CreatePlaneUseCaseRequest useCaseRequest = CreatePlaneUseCaseRequest
        .builder()
        .model(request.getModel())
        .description(request.getDescription())
        .benefitsId(request.getBenefitsId())
        .build();
    createPlaneUseCase.invoke(useCaseRequest, file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @GetMapping("/{plane_id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PlaneDetailResponse> getPlaneDetail(
      @PathVariable(value = "plane_id") String planeId) {
    return new BaseResponse<>(
        HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        getPlaneDetailUseCase.invoke(planeId));
  }

  @GetMapping("/booking-status")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<BookingStatusResponse> getPlaneFareStatus(
      @Validated @RequestBody GetPlaneFareStatusRequest request) {
    return new BaseResponse<>(
        HttpStatus.OK.getReasonPhrase(),
        System.currentTimeMillis(),
        getPlaneFareStatusUsecase.invoke(request.getFlightId(), request.getFareId()));
  }

  @PutMapping("/{plane_id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> updatePlane(
      @PathVariable(value = "plane_id") String planeId,
      @Valid @RequestPart("templateRequest") UpdatePlaneRequest request,
      @RequestBody MultipartFile file)
      throws IOException {
    UpdatePlaneUseCaseRequest updatePlaneUseCaseRequest =
        UpdatePlaneUseCaseRequest.builder()
            .model(request.getModel())
            .description(request.getDescription())
            .benefitsId(request.getBenefitsId())
            .build();
    updatePlaneUseCase.invoke(planeId, updatePlaneUseCaseRequest, file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}
