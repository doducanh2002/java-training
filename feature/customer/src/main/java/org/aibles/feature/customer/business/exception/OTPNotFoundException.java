package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.NotFoundException;

public class OTPNotFoundException extends NotFoundException {

  public OTPNotFoundException(String email) {
    setCode("org.aibles.feature.customer.business.exception.OTPNotFoundException");
    addParams("email", email);
  }
}
