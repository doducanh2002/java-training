package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.ReservedSeat;

public interface ReservedSeatRepository {
  List<String> getReservedSeatsByTicketId(String ticketId);

  void saveAll(List<ReservedSeat> reservedSeats);
}
