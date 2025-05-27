package org.aibles.feature.booking.business.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.business.dto.response.BenefitResponse;
import org.aibles.feature.booking.business.dto.response.PlaneResponse;
import org.aibles.feature.booking.business.usecase.GetListPlaneUsecase;
import org.aibles.feature.booking.entity.Plane;

public class GetListPlaneUsecaseImpl implements GetListPlaneUsecase {

  private final PlaneRepository planeRepository;

  public GetListPlaneUsecaseImpl(PlaneRepository planeRepository) {
    this.planeRepository = planeRepository;
  }

  @Override
  public List<PlaneResponse> invoke() {
    List<Plane> planesWithBenefits = planeRepository.findPlanesWithBenefits();
    List<PlaneResponse> responses = new ArrayList<>();

    for (Plane result : planesWithBenefits) {
      PlaneResponse planeResponse = PlaneResponse.from(result);
      List<BenefitResponse> benefitResponses = result.getBenefits().stream()
          .map(benefits -> {
            BenefitResponse benefitResponse = new BenefitResponse();
            benefitResponse.setId(benefits.getId());
            benefitResponse.setName(benefits.getName());
            return benefitResponse;
          }).toList();
      planeResponse.setBenefits(benefitResponses);
      responses.add(planeResponse);
    }

    return responses;
  }
}