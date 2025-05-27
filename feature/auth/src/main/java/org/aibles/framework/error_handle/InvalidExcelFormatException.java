package org.aibles.framework.error_handle;

import org.aibles.business.exception.BadRequestException;

public class InvalidExcelFormatException extends BadRequestException {
    public InvalidExcelFormatException() {
      setCode("org.aibles.feature.booking.business.exception.InvalidExcelFormatException");
    }
}
