package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class TotalPriceWrongFormatException extends BadRequestException {

  public TotalPriceWrongFormatException() {
    setCode("org.aibles.feature.booking.business.exception.TotalPriceWrongFormatException");
  }
}
