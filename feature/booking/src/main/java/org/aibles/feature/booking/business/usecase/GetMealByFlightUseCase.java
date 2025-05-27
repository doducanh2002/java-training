package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.MealResponse;

public interface GetMealByFlightUseCase {

  List<MealResponse> invoke(String flightId);
}
