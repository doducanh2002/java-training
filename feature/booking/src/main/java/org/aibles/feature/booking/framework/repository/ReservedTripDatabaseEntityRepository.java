package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.ReservedTripDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedTripDatabaseEntityRepository extends JpaRepository<ReservedTripDatabaseEntity, Long> {

}
