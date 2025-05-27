package org.aibles.framework.error_handle;

import org.aibles.business.exception.BadRequestException;
public class InvalidHeaderExcelException extends BadRequestException {

  public InvalidHeaderExcelException() {
    setCode("org.aibles.feature.booking.business.exception.InvalidHeaderExcelException");
  }
}