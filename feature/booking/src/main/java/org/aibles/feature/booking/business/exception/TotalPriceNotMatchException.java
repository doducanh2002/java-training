package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class TotalPriceNotMatchException extends BadRequestException {

  public TotalPriceNotMatchException() {
    setCode("org.aibles.feature.booking.business.exception.TotalPriceNotMatchException");
  }
}
