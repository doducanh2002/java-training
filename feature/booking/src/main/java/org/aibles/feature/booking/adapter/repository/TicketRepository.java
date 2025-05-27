package org.aibles.feature.booking.adapter.repository;

import java.util.Optional;
import org.aibles.feature.booking.entity.Ticket;

public interface TicketRepository {

  Optional<Ticket> getTicketByConfirmationCode(String confirmationCode);

  Optional<Ticket> getTicketByTicketId(String ticketId);

  boolean existsById(String id);

  void save(Ticket ticket);
}
