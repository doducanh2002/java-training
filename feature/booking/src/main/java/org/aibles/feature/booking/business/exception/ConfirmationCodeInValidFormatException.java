package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class ConfirmationCodeInValidFormatException extends BadRequestException {

  public ConfirmationCodeInValidFormatException(String confirmationCode) {
    setCode("org.aibles.feature.booking.business.exception.ConfirmationCodeInValidFormatException");
    addParams("confirmationCode", confirmationCode);
  }
}
