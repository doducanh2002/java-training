package org.aibles.business.exception;

public class PasswordIncorrectException extends BadRequestException {

  public PasswordIncorrectException() {
    setCode("org.aibles.business.exception.PasswordIncorrectException");
  }
}
