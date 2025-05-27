package org.aibles.feature.booking.framework.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.TicketRepository;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.framework.datasource.TicketDatabaseEntity;
import org.aibles.feature.booking.framework.repository.TicketDatabaseEntityRepository;
import org.aibles.feature.booking.util.TicketMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TicketDatasourceImpl implements TicketRepository {

  private final TicketDatabaseEntityRepository ticketDatabaseEntityRepository;

  @Override
  public Optional<Ticket> getTicketByConfirmationCode(String confirmationCode) {
    Optional<TicketDatabaseEntity> ticketDatabaseEntity = ticketDatabaseEntityRepository.findByConfirmationCode(
        confirmationCode);
    return ticketDatabaseEntity.map(TicketMapping::convertDatabaseToEntity);
  }

  @Override
  public boolean existsById(String id) {
    return ticketDatabaseEntityRepository.existsById(id);
  }

  @Override
  @Transactional
  public void save(Ticket ticket) {
    ticketDatabaseEntityRepository.save(TicketMapping.convertEntityToDatabase(ticket));
  }

  @Override
  public Optional<Ticket> getTicketByTicketId(String ticketId) {
    return ticketDatabaseEntityRepository.findById(ticketId).map(TicketMapping :: convertDatabaseToEntity);
  }
}
