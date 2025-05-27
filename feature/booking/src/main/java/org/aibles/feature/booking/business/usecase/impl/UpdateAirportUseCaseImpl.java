package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.business.dto.request.UpdateAirportUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.AirportResponse;
import org.aibles.feature.booking.business.exception.AirportNotFoundException;
import org.aibles.feature.booking.business.usecase.UpdateAirportUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateAirportUseCaseImpl implements UpdateAirportUseCase {

  private static final Logger log = LoggerFactory.getLogger(UpdateAirportUseCaseImpl.class);

  private final AirportRepository airportRepository;

  public UpdateAirportUseCaseImpl(AirportRepository airportRepository) {
    this.airportRepository = airportRepository;
  }

  @Override
  public AirportResponse invoke(String airportId, UpdateAirportUseCaseRequest request) {
    var airport = airportRepository
        .findById(airportId)
        .orElseThrow(() -> {
          log.error("(invoke)airportId: {}", airportId);
          throw new AirportNotFoundException(airportId);
        });
    airport.setName(request.getName());
    airport.setCode(request.getCode());
    airport.setCountry(request.getCountry());
    airport.setRegion(request.getRegion());
    return AirportResponse.from(airportRepository.save(airport));
  }
}
