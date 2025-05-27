package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.TravelClassResponse;

public interface GetListTravelClassUseCase {

  List<TravelClassResponse> invoke();
}
