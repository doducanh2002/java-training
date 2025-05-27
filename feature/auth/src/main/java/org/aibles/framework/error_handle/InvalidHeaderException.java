package org.aibles.framework.error_handle;

import org.springframework.security.core.AuthenticationException;

public class InvalidHeaderException extends AuthenticationException {

  public InvalidHeaderException(String msg) {
    super(msg);
  }
}
