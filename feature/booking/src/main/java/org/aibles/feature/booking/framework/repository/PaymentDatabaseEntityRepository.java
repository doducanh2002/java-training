package org.aibles.feature.booking.framework.repository;

import jakarta.transaction.Transactional;
import org.aibles.feature.booking.framework.datasource.PaymentDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDatabaseEntityRepository extends JpaRepository<PaymentDatabaseEntity, String> {
    @Modifying
    @Transactional
    @Query("update PaymentDatabaseEntity p set p.paymentStatus= :status where p.ticketId= :ticketId")
    void updateByTicketId(String ticketId, String status);
}
