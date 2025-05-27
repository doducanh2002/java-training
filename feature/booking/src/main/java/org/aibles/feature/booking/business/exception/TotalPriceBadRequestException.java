package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class TotalPriceBadRequestException extends BadRequestException {

  public TotalPriceBadRequestException() {
    setCode("org.aibles.feature.booking.business.exception.TotalPriceBadRequestException");
  }
}
