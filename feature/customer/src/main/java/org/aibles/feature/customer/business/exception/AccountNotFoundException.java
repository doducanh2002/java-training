package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {

  public AccountNotFoundException(String  id) {
    setCode("org.aibles.feature.customer.business.exception.AccountNotFoundException");
    addParams("id", id);
  }
}
