package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.TravelClassDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.TravelClassProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelClassDatabaseEntityRepository
    extends JpaRepository<TravelClassDatabaseEntity, String> {

  @Query(
      """
          SELECT tc from TravelClassDatabaseEntity tc
          LEFT JOIN PlaneTravelClassDatabaseEntity ptc ON tc.id = ptc.travelClassId
          LEFT JOIN PlaneDatabaseEntity p ON ptc.planeId = p.id
          """
  )
  List<TravelClassDatabaseEntity> findAllByPlaneId(String planeId);

  @Query("SELECT tv  from TravelClassDatabaseEntity tv WHERE LOWER(tv.name) = :name ")
  Optional<TravelClassDatabaseEntity> findByName(String name);

  @Query(value = """       
          SELECT tc.id AS id, tc.name AS name, s.id AS seatId, 
          s.seatRow AS seatRow, s.seatColumn AS seatColumn, s.seatPitch AS seatPitch, 
          s.seatWidth AS seatWidth
          
          FROM TravelClassDatabaseEntity tc
          JOIN PlaneTravelClassDatabaseEntity ptc ON ptc.travelClassId = tc.id
          JOIN SeatDatabaseEntity s ON  s.planeTravelClassId = ptc.id
          WHERE ptc.planeId = :planeId
          """
  )
  List<TravelClassProjection> findByPlaneId(String planeId);
}
