package org.aibles.business.exception;

public class EmailAlreadyExistedException extends ConflictException {

  public EmailAlreadyExistedException(String email) {
    setCode("org.aibles.business.exception.EmailAlreadyExistedException");
    addParams("email", email);
  }
}
