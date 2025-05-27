package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.PassengerAssistanceDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerAssistanceDatabaseEntityRepository extends JpaRepository<PassengerAssistanceDatabaseEntity, Long> {

}
