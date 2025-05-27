package org.aibles.feature.customer.business.exception;

import org.aibles.business.exception.BadRequestException;

public class InvalidOtpException extends BadRequestException {

  public InvalidOtpException(String otp) {
    setCode("org.aibles.feature.customer.business.exception.InvalidOtpException");
    addParams("otp", otp);
  }
}
