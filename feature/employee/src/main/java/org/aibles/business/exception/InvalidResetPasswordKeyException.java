package org.aibles.business.exception;

public class InvalidResetPasswordKeyException extends BadRequestException {

  public InvalidResetPasswordKeyException(String resetPasswordKey) {
    setCode("org.aibles.business.exception.InvalidResetPasswordKeyException");
    addParams("resetPasswordKey", resetPasswordKey);
  }
}
