package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.ConflictException;

public class EmailAlreadyExistedException extends ConflictException {

  public EmailAlreadyExistedException(String email) {
    setCode("org.aibles.feature.customer.business.exception.EmailAlreadyExistedException");
    addParams("email", email);
  }
}
