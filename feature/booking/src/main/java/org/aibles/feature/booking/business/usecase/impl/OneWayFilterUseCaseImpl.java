package org.aibles.feature.booking.business.usecase.impl;

import java.time.LocalDate;
import java.util.List;
import org.aibles.feature.booking.business.dto.request.OneWayFilterUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;
import org.aibles.feature.booking.business.exception.FlightDateInvalidException;
import org.aibles.feature.booking.business.usecase.FilterTripsUseCase;
import org.aibles.feature.booking.business.usecase.OneWayFilterUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneWayFilterUseCaseImpl implements OneWayFilterUseCase {

  private static final Logger log = LoggerFactory.getLogger(OneWayFilterUseCaseImpl.class);

  private final FilterTripsUseCase filterTripsUseCase;

  public OneWayFilterUseCaseImpl(FilterTripsUseCase filterTripsUseCase) {
    this.filterTripsUseCase = filterTripsUseCase;
  }

  @Override
  public List<FilterTripsResponse> invoke(OneWayFilterUseCaseRequest request) {
    if (request.getDate().isBefore(LocalDate.now())) {
      throw new FlightDateInvalidException(List.of(request.getDate().toString()));
    }
    return filterTripsUseCase.invoke(request.getDepartureAirportId(),
        request.getArrivalAirportId(), request.getDate());
  }
}
