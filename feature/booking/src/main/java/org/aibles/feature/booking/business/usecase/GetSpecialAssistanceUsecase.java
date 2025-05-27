package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.SpecialAssistanceResponse;

public interface GetSpecialAssistanceUsecase {
  List<SpecialAssistanceResponse> invoke();
}
