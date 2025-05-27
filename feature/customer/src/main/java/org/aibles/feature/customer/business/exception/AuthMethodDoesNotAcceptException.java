package org.aibles.feature.customer.business.exception;

import org.aibles.business.BaseException;

public class AuthMethodDoesNotAcceptException extends BaseException {
  public AuthMethodDoesNotAcceptException() {
    setStatus(401);
    setCode("org.aibles.feature.customer.business.exception.InvalidResetPasswordKeyException");
  }
}
