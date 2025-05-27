package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.NotFoundException;

public class UsernameNotFoundException extends NotFoundException {

  public UsernameNotFoundException(String username) {
    setCode("org.aibles.feature.customer.business.exception.UsernameNotFoundException");
    addParams("username", username);
  }
}
