package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class PaypalOrderNotFoundException extends NotFoundException {
  public PaypalOrderNotFoundException(String orderId) {
    setCode("org.aibles.feature.booking.business.exception.PaypalOrderNotFoundException");
    addParams("orderId", orderId);
  }
}
