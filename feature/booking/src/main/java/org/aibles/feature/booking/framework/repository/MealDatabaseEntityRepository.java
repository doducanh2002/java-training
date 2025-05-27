package org.aibles.feature.booking.framework.repository;

import java.util.List;
import org.aibles.feature.booking.framework.datasource.MealDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MealDatabaseEntityRepository extends JpaRepository<MealDatabaseEntity, String>,
    JpaSpecificationExecutor<MealDatabaseEntity> {

  List<MealDatabaseEntity> findAllByFlightId(String flightId);

  List<MealDatabaseEntity> findAllByIdIn(List<String> flightId);
}
