package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class FareNotFoundException extends NotFoundException {

  public FareNotFoundException(String fareId) {
    setCode("org.aibles.feature.booking.business.exception.FareNotFoundException");
    addParams("fareId", fareId);
  }
}
