package org.aibles.framework.security.impl;

import org.aibles.framework.security.AuthenticationFactory;
import org.aibles.framework.security.AuthenticationProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFactoryImpl implements AuthenticationFactory {
  private final ApplicationContext applicationContext;

  public AuthenticationFactoryImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public AuthenticationProvider getAuthenticationProvider(String tokenType) {
    return applicationContext.getBean(tokenType.toUpperCase() + "_" + "AUTHENTICATION_PROVIDER", AuthenticationProvider.class);
  }
}
