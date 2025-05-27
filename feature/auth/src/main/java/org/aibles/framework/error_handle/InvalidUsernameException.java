package org.aibles.framework.error_handle;

import org.springframework.security.core.AuthenticationException;

public class InvalidUsernameException extends AuthenticationException {

  public InvalidUsernameException(String msg) {
    super(msg);
  }
}
