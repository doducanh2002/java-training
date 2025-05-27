package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class TripNotFoundException extends NotFoundException {

  public TripNotFoundException(String tripId) {
    setCode("org.aibles.feature.booking.business.exception.TripNotFoundException");
    addParams("tripId", tripId);
  }
}
