package org.aibles.feature.booking.business.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.dto.response.TravelClassResponse;
import org.aibles.feature.booking.business.usecase.GetListTravelClassUseCase;
import org.aibles.feature.booking.entity.TravelClass;

public class GetListTravelClassUseCaseImpl implements GetListTravelClassUseCase {

  private final TravelClassRepository travelClassRepository;

  public GetListTravelClassUseCaseImpl(TravelClassRepository travelClassRepository) {
    this.travelClassRepository = travelClassRepository;
  }

  @Override
  public List<TravelClassResponse> invoke() {
    List<TravelClass> travelClasses = travelClassRepository.getTravelClassList();
    List<TravelClassResponse> travelClassResponses = new ArrayList<>();
    for (TravelClass travelClass : travelClasses) {
      TravelClassResponse travelClassResponse = TravelClassResponse.from(travelClass);
      travelClassResponses.add(travelClassResponse);
    }
    return travelClassResponses;
  }
}
