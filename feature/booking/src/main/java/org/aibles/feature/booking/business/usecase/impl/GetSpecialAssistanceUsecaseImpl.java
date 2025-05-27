package org.aibles.feature.booking.business.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.SpecialAssistanceRepository;
import org.aibles.feature.booking.business.dto.response.SpecialAssistanceResponse;
import org.aibles.feature.booking.business.usecase.GetSpecialAssistanceUsecase;
import org.aibles.feature.booking.entity.SpecialAssistance;

public class GetSpecialAssistanceUsecaseImpl implements GetSpecialAssistanceUsecase {
  private final SpecialAssistanceRepository specialAssistanceRepository;

  public GetSpecialAssistanceUsecaseImpl(SpecialAssistanceRepository specialAssistanceRepository) {
    this.specialAssistanceRepository = specialAssistanceRepository;
  }

  @Override
  public List<SpecialAssistanceResponse> invoke() {
    List<SpecialAssistance> specialAssistanceList = specialAssistanceRepository.findAll();
    List<SpecialAssistanceResponse> response = new ArrayList<>();
    for (SpecialAssistance specialAssistance : specialAssistanceList) {
      SpecialAssistanceResponse specialAssistanceResponse =
          SpecialAssistanceResponse.from(specialAssistance);
      response.add(specialAssistanceResponse);
    }
    return response;
  }
}
