package org.aibles.feature.booking.framework.repository;

import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.TicketDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDatabaseEntityRepository extends JpaRepository<TicketDatabaseEntity, String> {

  Optional<TicketDatabaseEntity> findByConfirmationCode(String confirmationCode);
}
