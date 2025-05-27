package org.aibles.feature.booking.business.usecase.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.business.dto.request.RoundTripFilterUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;
import org.aibles.feature.booking.business.exception.FlightDateInvalidException;
import org.aibles.feature.booking.business.usecase.FilterTripsUseCase;
import org.aibles.feature.booking.business.usecase.RoundTripFilterUseCase;
import org.aibles.feature.booking.constant.RoundTripDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoundTripFilterUseCaseImpl implements RoundTripFilterUseCase {

  private static final Logger log = LoggerFactory.getLogger(RoundTripFilterUseCaseImpl.class);

  private final FilterTripsUseCase filterTripsUseCase;

  public RoundTripFilterUseCaseImpl(FilterTripsUseCase filterTripsUseCase) {
    this.filterTripsUseCase = filterTripsUseCase;
  }

  @Override
  public List<FilterTripsResponse> invoke(RoundTripFilterUseCaseRequest request) {
    if (!isFlightDateValid(request.getDepartureDate(), request.getArrivalDate())) {
      throw new FlightDateInvalidException(List.of(request.getDepartureDate().toString(),
          request.getArrivalDate().toString()));
    }
    List<FilterTripsResponse> responses = new ArrayList<>();
    RoundTripDirection direction = request.getDirection();
    switch (direction) {
      case FORWARD -> responses = filterTripsUseCase.invoke(request.getDepartureAirportId(),
          request.getArrivalAirportId(),
          request.getDepartureDate());
      case BACKWARD -> responses = filterTripsUseCase.invoke(request.getArrivalAirportId(),
          request.getDepartureAirportId(),
          request.getArrivalDate());
    }
    return responses;
  }

  private boolean isFlightDateValid(LocalDate departureDate, LocalDate arrivalDate) {
    return !departureDate.isBefore(LocalDate.now()) && departureDate.isBefore(arrivalDate);
  }
}
