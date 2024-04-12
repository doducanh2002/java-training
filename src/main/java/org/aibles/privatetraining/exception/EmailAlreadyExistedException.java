package org.aibles.privatetraining.exception;

public class EmailAlreadyExistedException extends BaseException{

  public EmailAlreadyExistedException(String email) {
    setCode("org.aibles.privatetraining.exception.EmailAlreadyExistedException");
    setStatus(409);
    addParams("Email already exist", email);
  }
}