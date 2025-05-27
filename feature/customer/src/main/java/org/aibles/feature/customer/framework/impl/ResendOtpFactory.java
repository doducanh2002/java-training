package org.aibles.feature.customer.framework.impl;

import lombok.RequiredArgsConstructor;
import org.aibles.feature.customer.business.usecase.*;
import org.aibles.feature.customer.framework.constant.ResendOtpCase;
import org.aibles.feature.customer.framework.controller.dto.request.ResendOtpRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResendOtpFactory {
  private final ApplicationContext applicationContext;

  public void invoke(ResendOtpRequest request) {
    ResendOtpCase resendOtpCase = ResendOtpCase.valueOf(request.getType());
    Object resendOtpBean = applicationContext.getBean(resendOtpCase.getName());
    switch (resendOtpCase) {
      case ACTIVE_ACCOUNT -> ((SendOtpActiveUseCase) resendOtpBean).invoke(request.getEmail());
      case UNLOCK_ACCOUNT -> ((SendOtpUnlockAccountUseCase) resendOtpBean).invoke(request.getEmail());
      case FORGOT_PASSWORD -> ((ForgotPasswordUseCase) resendOtpBean).invoke(request.getEmail());
    }
  }
}
