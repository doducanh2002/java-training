package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.ConflictException;

public class PasswordConfirmNotMatchException extends ConflictException {

  public PasswordConfirmNotMatchException(String password, String confirmPassword) {
    setCode("org.aibles.feature.customer.business.exception.PasswordConfirmNotMatchException");
    addParams("password", password);
    addParams("confirmPassword", confirmPassword);
  }
}
