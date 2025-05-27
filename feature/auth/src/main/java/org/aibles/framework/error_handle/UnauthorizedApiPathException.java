package org.aibles.framework.error_handle;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedApiPathException extends AuthenticationException {

  public UnauthorizedApiPathException(String msg) {
    super(msg);
  }
}
