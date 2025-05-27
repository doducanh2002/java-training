package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class OTPInvalidException extends BadRequestException {
  public OTPInvalidException(String otp) {
    setCode("org.aibles.feature.customer.business.exception.OTPInvalidException");
    addParams("otp", otp);
  }
}
