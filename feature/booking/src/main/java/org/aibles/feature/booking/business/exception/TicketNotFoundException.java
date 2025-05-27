package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class TicketNotFoundException extends NotFoundException {

  public TicketNotFoundException(String ticketId) {
    setCode("org.aibles.feature.booking.business.exception.TicketNotFoundException");
    addParams("ticketId", ticketId);
  }
}
