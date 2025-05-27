package org.aibles.business.exception;

public class EmailSendingException extends InternalErrorException{

  public EmailSendingException(String email) {
    setStatus(500);
    setCode("org.aibles.business.exception.EmailSendingException");
    addParams("email", email);
  }
}
