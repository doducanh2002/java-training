package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class AccountLockedException extends BadRequestException {

  public AccountLockedException(String username) {
    setCode("org.aibles.feature.customer.business.exception.AccountLockedException");
    addParams("username", username);
  }
}
