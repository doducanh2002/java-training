package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class PlaneNotFoundException extends NotFoundException {

  public PlaneNotFoundException(String planeId) {
    setCode("org.aibles.feature.booking.business.exception.PlaneNotFoundException");
    addParams("planeId", planeId);
  }
}
