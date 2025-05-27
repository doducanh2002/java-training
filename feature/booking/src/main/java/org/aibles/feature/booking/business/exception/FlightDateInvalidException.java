package org.aibles.feature.booking.business.exception;

import java.util.List;
import org.aibles.business.exception.BadRequestException;

public class FlightDateInvalidException extends BadRequestException {

  public FlightDateInvalidException(List<String> date) {
      setCode("org.aibles.feature.booking.business.exception.FlightDateInvalidException");
      addParams("date", date.toString());
  }
}
