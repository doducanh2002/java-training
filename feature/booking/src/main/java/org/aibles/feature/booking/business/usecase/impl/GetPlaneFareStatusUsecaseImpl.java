package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.business.dto.response.BookingStatusResponse;
import org.aibles.feature.booking.business.exception.FareNotFoundException;
import org.aibles.feature.booking.business.exception.FlightNotFoundException;
import org.aibles.feature.booking.business.usecase.GetPlaneFareStatusUsecase;

public class GetPlaneFareStatusUsecaseImpl implements GetPlaneFareStatusUsecase {

  private final FareRepository fareRepository;
  private final FlightRepository flightRepository;

  public GetPlaneFareStatusUsecaseImpl(FareRepository fareRepository,
      FlightRepository flightRepository) {
    this.fareRepository = fareRepository;
    this.flightRepository = flightRepository;
  }

  @Override
  public BookingStatusResponse invoke(String flightId, String fareId) {
    if (!fareRepository.existsById(fareId)) {
      throw new FareNotFoundException(fareId);
    }
    if (!flightRepository.existsById(flightId)) {
      throw new FlightNotFoundException(flightId);
    }
    return new BookingStatusResponse(fareRepository.getPlaneFareStatus(flightId, fareId));
  }
}
