package org.aibles.feature.booking.business.usecase.impl;

import java.util.List;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.dto.response.BenefitResponse;
import org.aibles.feature.booking.business.dto.response.PlaneResponse;
import org.aibles.feature.booking.business.dto.response.planedetail.PlaneDetailResponse;
import org.aibles.feature.booking.business.dto.response.planedetail.TravelClassDetailResponse;
import org.aibles.feature.booking.business.exception.PlaneNotFoundException;
import org.aibles.feature.booking.business.usecase.GetPlaneDetailUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetPlaneDetailUseCaseImpl implements GetPlaneDetailUseCase {

  private final Logger log = LoggerFactory.getLogger(GetPlaneDetailUseCaseImpl.class);

  private final PlaneRepository planeRepository;
  private final TravelClassRepository travelClassRepository;

  public GetPlaneDetailUseCaseImpl(PlaneRepository planeRepository,
      TravelClassRepository travelClassRepository) {
    this.planeRepository = planeRepository;
    this.travelClassRepository = travelClassRepository;
  }

  @Override
  public PlaneDetailResponse invoke(String planeId) {

    var plane = planeRepository
        .findById(planeId)
        .orElseThrow(() -> {
          log.error("(invoke)Plane not found: {}", planeId);
          throw new PlaneNotFoundException(planeId);
        });
    PlaneResponse planeResponse = PlaneResponse.from(plane);

    List<BenefitResponse> benefitResponses = plane.getBenefits().stream()
        .map(benefits -> {
          BenefitResponse benefitResponse = new BenefitResponse();
          benefitResponse.setId(benefits.getId());
          benefitResponse.setName(benefits.getName());
          return benefitResponse;
        }).toList();
    planeResponse.setBenefits(benefitResponses);

    List<TravelClassDetailResponse> travelClassDetailResponses = travelClassRepository.findByPlaneId(planeId);
    return new PlaneDetailResponse(planeResponse, travelClassDetailResponses);
  }
}
