package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.FareDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.FareBenefitProjection;
import org.aibles.feature.booking.framework.impl.projection.SeatStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FareDatabaseEntityRepository extends JpaRepository<FareDatabaseEntity, String> {

  @Query("""
      SELECT f.id AS fareId,
      f.name AS fareName,
      f.price AS price,
      b.id AS benefitId,
      b.name AS benefitName
      FROM FareDatabaseEntity f
      JOIN FareBenefitDatabaseEntity fb ON f.id = fb.fareId
      JOIN BenefitDatabaseEntity b ON b.id = fb.benefitId
      JOIN TravelClassDatabaseEntity t on t.id = f.travelClassId
      WHERE t.id = :travelClassId
      """)
  List<FareBenefitProjection> findDetailByTravelClassId(String travelClassId);

  Optional<FareDatabaseEntity> findByName(String name);

  @Query("""
          SELECT s.id          AS seatId,
                 s.seatColumn AS seatColumn,
                 s.seatPitch  AS seatPitch,
                 s.seatRow    AS seatRow,
                 s.seatWidth  AS seatWidth
          FROM FareDatabaseEntity f
                   JOIN PlaneTravelClassDatabaseEntity ptc ON ptc.travelClassId = f.travelClassId
                   JOIN FlightDatabaseEntity fl ON fl.planeId = ptc.planeId AND fl.id = :flightId
                   JOIN SeatDatabaseEntity s ON s.planeTravelClassId = ptc.id
          WHERE f.id = :fareId
      """)
  List<SeatStatusProjection> findByFlightIdAndFareId(String flightId, String fareId);

  @Query("""
    select f From FareDatabaseEntity f where f.id in :ids
    """)
  List<FareDatabaseEntity> findByIds(List<String> ids);
}
