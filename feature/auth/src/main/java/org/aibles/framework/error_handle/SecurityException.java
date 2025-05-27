package org.aibles.framework.error_handle;

import java.util.HashMap;
import java.util.Map;

public enum SecurityException {

  INVALID_TOKEN("org.aibles.framework.error_handle.InvalidTokenException"),
  INVALID_API_PATH("org.aibles.framework.error_handle.UnauthorizedApiPathException"),
  INVALID_HEADER("org.aibles.framework.error_handle.InvalidHeaderException"),
  EXPIRED_TOKEN("org.aibles.framework.error_handle.ExpiredTokenException"),
  INVALID_USERNAME("org.aibles.framework.error_handle.InvalidUsernameException"),
  AUTHENTICATION_EXCEPTION("org.springframework.security.core.AuthenticationException"),
  AUTHORIZATION_EXCEPTION("org.aibles.framework.error_handle.AuthorizationException"),
  INVALID_TOKEN_TYPE_EXCEPTION("org.aibles.framework.error_handle.InvalidTokenTypeException");

  private final String msgCode;

  SecurityException(String msgCode) {
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

  private static final Map<String, SecurityException> enumMap = new HashMap<>();

  static {
    for (SecurityException e : SecurityException.values()) {
      enumMap.put(e.getMsgCode(), e);
    }
  }

  public static SecurityException resolve(String msgCode) {
    return enumMap.get(msgCode);
  }
}
