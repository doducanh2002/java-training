package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PasswordSimilarException extends BadRequestException {

  public PasswordSimilarException() {
    setCode("org.aibles.feature.customer.business.exception.PasswordSimilarException");
  }
}
