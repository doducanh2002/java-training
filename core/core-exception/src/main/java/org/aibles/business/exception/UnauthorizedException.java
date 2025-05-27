package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException() {
    setStatus(401);
    setCode("org.aibles.business.exception.UnauthorizedException");
  }
}
