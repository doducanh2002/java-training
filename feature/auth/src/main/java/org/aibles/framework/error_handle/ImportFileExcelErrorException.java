package org.aibles.framework.error_handle;

import org.aibles.business.exception.BadRequestException;

public class ImportFileExcelErrorException extends BadRequestException {

  public ImportFileExcelErrorException() {
    setCode("org.aibles.feature.booking.business.exception.ImportFileExcelErrorException");
  }
}
