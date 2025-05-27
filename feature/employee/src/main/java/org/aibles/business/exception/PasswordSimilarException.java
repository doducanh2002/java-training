package org.aibles.business.exception;

public class PasswordSimilarException extends BadRequestException {

  public PasswordSimilarException() {
    setCode("org.aibles.business.exception.PasswordSimilarException");
  }
}
