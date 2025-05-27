package org.aibles.framework.security;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthenticationProvider {

  boolean validateAccessToken(String token);

  void addAuthentication(String token) throws JsonProcessingException;

}
