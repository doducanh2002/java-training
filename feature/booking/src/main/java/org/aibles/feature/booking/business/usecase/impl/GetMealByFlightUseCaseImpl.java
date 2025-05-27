package org.aibles.feature.booking.business.usecase.impl;

import java.util.List;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.MealRepository;
import org.aibles.feature.booking.business.dto.response.MealResponse;
import org.aibles.feature.booking.business.exception.FlightNotFoundException;
import org.aibles.feature.booking.business.usecase.GetMealByFlightUseCase;

public class GetMealByFlightUseCaseImpl implements GetMealByFlightUseCase {

  private final FlightRepository flightRepository;

  private final MealRepository mealRepository;

  public GetMealByFlightUseCaseImpl(FlightRepository flightRepository, MealRepository mealRepository) {
    this.flightRepository = flightRepository;
    this.mealRepository = mealRepository;
  }

  @Override
  public List<MealResponse> invoke(String flightId) {

    if (!flightRepository.existsById(flightId)) {
      throw new FlightNotFoundException(flightId);
    }
    return mealRepository.getMealByFlightId(flightId).stream()
        .map(MealResponse::from).toList();
  }
}
