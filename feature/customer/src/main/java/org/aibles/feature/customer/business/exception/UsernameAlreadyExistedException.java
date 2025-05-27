package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.ConflictException;

public class UsernameAlreadyExistedException extends ConflictException {

  public UsernameAlreadyExistedException(String username) {
    setCode("org.aibles.feature.customer.business.exception.UsernameAlreadyExistedException");
    addParams("username", username);
  }
}
