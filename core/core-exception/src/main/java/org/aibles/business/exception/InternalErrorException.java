package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class InternalErrorException extends BaseException {
  public InternalErrorException() {
    setStatus(500);
    setCode("org.aibles.business.exception.InternalErrorException");
  }
}
