package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class NamePlaneNotFoundException extends NotFoundException {

  public NamePlaneNotFoundException(String namePlane) {
    setCode("org.aibles.feature.booking.business.exception.NamePlaneNotFoundException");
    addParams("namePlane", namePlane);
  }
}
