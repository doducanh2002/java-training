package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.TripContactDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripContactDatabaseEntityRepository extends JpaRepository<TripContactDatabaseEntity, Long> {

}
