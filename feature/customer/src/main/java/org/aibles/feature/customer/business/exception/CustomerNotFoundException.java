package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

  public CustomerNotFoundException(String accountId) {
    setCode("org.aibles.feature.customer.business.exception.CustomerNotFoundException");
    addParams("accountId", accountId);
  }
}
