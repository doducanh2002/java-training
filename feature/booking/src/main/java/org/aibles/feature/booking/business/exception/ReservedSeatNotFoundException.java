package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class ReservedSeatNotFoundException extends NotFoundException {

  public ReservedSeatNotFoundException() {
    setCode("org.aibles.feature.booking.business.exception.ReservedSeatNotFoundException");
  }
}
