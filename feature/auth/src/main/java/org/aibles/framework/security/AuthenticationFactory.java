package org.aibles.framework.security;

public interface AuthenticationFactory {
  AuthenticationProvider getAuthenticationProvider(String tokenType);
}
