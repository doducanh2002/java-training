package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class ForbiddenException extends BaseException {

  public ForbiddenException() {
    setStatus(403);
    setCode("org.aibles.business.exception.ForbiddenException");
  }
}
