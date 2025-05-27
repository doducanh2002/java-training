package org.aibles.business.exception;

public class AccountByEmailNotFoundException extends NotFoundException {

  public AccountByEmailNotFoundException(String  email) {
    setCode("org.aibles.business.exception.AccountByEmailNotFoundException");
    addParams("email", email);
  }


}
