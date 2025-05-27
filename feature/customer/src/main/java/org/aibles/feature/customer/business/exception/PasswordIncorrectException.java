package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PasswordIncorrectException extends BadRequestException {

  public PasswordIncorrectException() {
    setCode("org.aibles.feature.customer.business.exception.PasswordIncorrectException");
  }
}
