package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

  public CustomerNotFoundException(String customerId) {
    setCode("org.aibles.feature.booking.business.exception.CustomerNotFoundException");
    addParams("customerId", customerId);
  }
}
