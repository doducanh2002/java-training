package org.aibles.feature.booking.framework.repository;

import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.PlaneTravelClassDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneTravelClassDatabaseEntityRepository
    extends JpaRepository<PlaneTravelClassDatabaseEntity, String> {

  @Query(
      """
      SELECT p FROM PlaneTravelClassDatabaseEntity p
      JOIN TravelClassDatabaseEntity t ON p.travelClassId = t.id
      WHERE p.planeId = :planeId AND t.name = :name
      """)
  Optional<PlaneTravelClassDatabaseEntity> findByNameAndPlaneId(String name, String planeId);
}
