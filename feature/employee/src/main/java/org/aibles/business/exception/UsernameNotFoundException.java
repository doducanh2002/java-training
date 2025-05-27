package org.aibles.business.exception;

public class UsernameNotFoundException extends NotFoundException {

  public UsernameNotFoundException(String username) {
    setCode("org.aibles.business.exception.UsernameNotFoundException");
    addParams("username", username);
  }
}
