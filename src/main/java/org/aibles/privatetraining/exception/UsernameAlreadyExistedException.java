package org.aibles.privatetraining.exception;

public class UsernameAlreadyExistedException extends BaseException{

  public UsernameAlreadyExistedException(String email) {
    setCode("org.aibles.privatetraining.exception.UsernameAlreadyExistedException");
    setStatus(409);
    addParams("Username already exist", email);
  }
}