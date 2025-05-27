package org.aibles.feature.booking.business.usecase.impl;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.dto.response.TravelClassResponse;
import org.aibles.feature.booking.business.dto.response.TripResponse;
import org.aibles.feature.booking.business.usecase.GetBookedTripsUseCase;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.entity.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBookedTripsUseCaseImpl implements GetBookedTripsUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      GetBookedTripsUseCaseImpl.class);

  private final TripRepository tripRepository;

  public GetBookedTripsUseCaseImpl(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  @Override
  public List<TripResponse> invoke(String accountId) {
    List<Trip> trips = tripRepository.findAllByAccountId (accountId);
    List<TripResponse> tripResponses = new ArrayList<>();
    for (Trip trip : trips) {
      tripResponses.add(TripResponse.from(trip));
    }
    return tripResponses;
  }
}
