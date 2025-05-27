package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class ConflictException extends BaseException {
  public ConflictException() {
    setStatus(409);
    setCode("org.aibles.business.exception.ConflictException");
  }
}
