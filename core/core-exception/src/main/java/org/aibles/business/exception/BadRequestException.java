package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class BadRequestException extends BaseException {
  public BadRequestException() {
    setStatus(400);
    setCode("org.aibles.business.exception.BadRequestException");
  }
}
