package org.aibles.business.exception;

public class AccountLockedException extends BadRequestException {

  public AccountLockedException() {
    setCode("org.aibles.business.exception.AccountLockedException");
  }
}
