package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PurchaseBadRequestException extends BadRequestException {

  public PurchaseBadRequestException() {
    setCode("org.aibles.feature.booking.business.exception.PurchaseBadRequestException");
  }
}
