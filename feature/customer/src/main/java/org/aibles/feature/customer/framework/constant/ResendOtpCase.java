package org.aibles.feature.customer.framework.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResendOtpCase {

  ACTIVE_ACCOUNT("sendOtpActiveUseCase"),
  FORGOT_PASSWORD( "forgotPasswordUseCase"),
  UNLOCK_ACCOUNT( "sendOtpUnlockAccountUseCase");

  private final String name;
}
