package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.adapter.repository.ReservedSeatRepository;
import org.aibles.feature.booking.entity.ReservedSeat;
import org.aibles.feature.booking.framework.repository.ReservedSeatDatabaseEntityRepository;
import org.aibles.feature.booking.util.ReservedSeatMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReservedSeatDatasourceImpl implements ReservedSeatRepository {

  private final ReservedSeatDatabaseEntityRepository reservedSeatDatabaseEntityRepository;

  @Override
  @Transactional
  public void saveAll(List<ReservedSeat> reservedSeats) {
    reservedSeatDatabaseEntityRepository.saveAll(
        ReservedSeatMapping.convertEntityToDatabase(reservedSeats));
  }

  @Override
  public List<String> getReservedSeatsByTicketId(String ticketId) {
    return reservedSeatDatabaseEntityRepository.getReservedSeatsByTicketId(ticketId);
  }
}
