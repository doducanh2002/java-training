package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class DisqualifiedCustomerException extends BadRequestException {

  public DisqualifiedCustomerException() {
    setCode("org.aibles.feature.booking.business.exception.DisqualifiedCustomerException");
  }
}
