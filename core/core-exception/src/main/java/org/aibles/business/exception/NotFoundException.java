package org.aibles.business.exception;

import org.aibles.business.BaseException;

public class NotFoundException extends BaseException {
  public NotFoundException() {
    setStatus(404);
    setCode("org.aibles.business.exception.NotFoundException");
  }
}
