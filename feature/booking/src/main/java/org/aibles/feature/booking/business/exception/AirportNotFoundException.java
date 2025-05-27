package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class AirportNotFoundException extends NotFoundException {

  public AirportNotFoundException(String airportId) {
    setCode("org.aibles.feature.booking.business.exception.AirportNotFoundException");
    addParams("airportId", airportId);
  }
}
