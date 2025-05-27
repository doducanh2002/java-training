package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class DuplicateFlightNumberException extends BadRequestException {

  public DuplicateFlightNumberException() {
    setCode("org.aibles.feature.booking.business.exception.DuplicateFlightNumberException");
  }
}
