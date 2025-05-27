package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class NumberSeatDoesNotMatchException extends BadRequestException {

  public NumberSeatDoesNotMatchException() {
    setCode("org.aibles.feature.booking.business.exception.NumberSeatDoesNotMatchException");
  }
}
