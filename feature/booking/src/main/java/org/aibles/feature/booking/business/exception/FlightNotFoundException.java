package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;
import org.aibles.feature.booking.entity.Flight;

public class FlightNotFoundException extends NotFoundException {

  public FlightNotFoundException(String id) {
    setCode("org.aibles.feature.booking.business.exception.FlightNotFoundException");
    addParams("flightId", id);
  }
}
