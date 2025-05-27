package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.PlaneTravelClass;
import org.aibles.feature.booking.framework.datasource.SeatDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDatabaseEntityRepository extends JpaRepository<SeatDatabaseEntity, String> {

  List<SeatDatabaseEntity> findAllByPlaneTravelClassId(String planeTravelClassId);

  Optional<SeatDatabaseEntity> findByPlaneTravelClassIdAndSeatRowAndSeatColumn(
      String planeTravelClassId, Integer seatRow, String seatColumn);

  @Query("SELECT s FROM SeatDatabaseEntity s JOIN PlaneTravelClassDatabaseEntity ptc ON s.planeTravelClassId = ptc.id JOIN PlaneDatabaseEntity p ON ptc.planeId = p.id JOIN TravelClassDatabaseEntity tc ON ptc.travelClassId = tc.id JOIN FareDatabaseEntity f ON f.travelClassId = tc.id JOIN FlightDatabaseEntity fl ON fl.planeId = p.id WHERE fl.id = :flightId AND f.id = :fareId")
  List<SeatDatabaseEntity> findSeatDatabaseEntities(String flightId, String fareId);
}