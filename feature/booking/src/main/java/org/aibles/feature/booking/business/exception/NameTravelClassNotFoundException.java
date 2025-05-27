package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class NameTravelClassNotFoundException extends BadRequestException {

  public NameTravelClassNotFoundException(String nameTravelClass) {
    setCode("org.aibles.feature.booking.business.exception.NameTravelClassNotFoundException");
    addParams("nameTravelClass", nameTravelClass);
  }
}
