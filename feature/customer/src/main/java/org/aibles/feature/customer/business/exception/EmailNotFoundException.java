package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.NotFoundException;

public class EmailNotFoundException extends NotFoundException {

  public EmailNotFoundException(String email) {
    setCode("org.aibles.feature.customer.business.exception.EmailNotFoundException");
    addParams("email", email);
  }
}
