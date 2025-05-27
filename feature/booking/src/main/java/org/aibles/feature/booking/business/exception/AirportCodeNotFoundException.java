package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class AirportCodeNotFoundException extends NotFoundException {

  public AirportCodeNotFoundException(String airportCode) {
    setCode("org.aibles.feature.booking.business.exception.AirportCodeNotFoundException");
    addParams("airportCode", airportCode);
  }
}
