package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class InvalidResetPasswordKeyException extends BadRequestException {

  public InvalidResetPasswordKeyException(String resetPasswordKey) {
    setCode("org.aibles.feature.customer.business.exception.InvalidResetPasswordKeyException");
    addParams("resetPasswordKey", resetPasswordKey);
  }
}
