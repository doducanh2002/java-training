package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.PassengerInfoDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerInfoDatabaseEntityRepository extends
    JpaRepository<PassengerInfoDatabaseEntity, String> {

  boolean existsByTicketIdAndLastName(String ticketId, String lastName);

  List<PassengerInfoDatabaseEntity> findByTicketId(String ticketId);
}
