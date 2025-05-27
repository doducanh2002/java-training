package org.aibles.business.exception;

public class PasswordConfirmNotMatchException extends ConflictException {

  public PasswordConfirmNotMatchException() {
    setCode("org.aibles.business.exception.PasswordConfirmNotMatchException");
  }
}
