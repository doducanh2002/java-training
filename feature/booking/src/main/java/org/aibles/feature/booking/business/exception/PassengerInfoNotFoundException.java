package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class PassengerInfoNotFoundException extends NotFoundException {

  public PassengerInfoNotFoundException() {
    setCode("org.aibles.feature.booking.business.exception.PassengerInfoNotFoundException");
  }
}
