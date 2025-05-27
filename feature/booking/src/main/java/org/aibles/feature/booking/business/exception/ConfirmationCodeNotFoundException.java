package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class ConfirmationCodeNotFoundException extends NotFoundException {

  public ConfirmationCodeNotFoundException(String confirmationCode) {
    setCode("org.aibles.feature.booking.business.exception.ConfirmationCodeNotFoundException");
    addParams("confirmationCode", confirmationCode);
  }
}
