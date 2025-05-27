package org.aibles.feature.booking.framework.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.FlightDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.FlightPlaneProjection;
import org.aibles.feature.booking.framework.impl.projection.FlightStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FlightDatabaseEntityRepository extends JpaRepository<FlightDatabaseEntity, String> {

  @Query(
      """
          SELECT
              f.id AS id,
              f.flightNumber AS flightNumber,
              f.airline AS airline,
              new org.aibles.feature.booking.framework.impl.projection.AirportDTO(a1.id, a1.name, a1.code, a1.country, a1.region) AS departureAirport,
              new org.aibles.feature.booking.framework.impl.projection.AirportDTO(a2.id, a2.name, a2.code, a2.country, a2.region) AS arrivalAirport,
              f.departureTime AS departureTime,
              f.arrivalTime AS arrivalTime,
              f.isStopStation AS stopStation,
              f.miles AS miles,
              f.status AS status
          FROM FlightDatabaseEntity f
          LEFT JOIN AirportDatabaseEntity a1 ON a1.id = f.departureAirportId
          LEFT JOIN AirportDatabaseEntity a2 ON a2.id = f.arrivalAirportId
          WHERE f.departureAirportId = :from AND
                f.arrivalAirportId = :to AND
                date(f.departureTime) = :date
          """)
  List<FlightStatusProjection> findAll(String from, String to, LocalDate date);

  @Query(
      """
          SELECT new org.aibles.feature.booking.framework.impl.projection.FlightPlaneProjection(
          f.id,
          f.flightNumber,
          f.airline,
          da.name,
          aa.name,
          f.departureTime,
          f.arrivalTime,
          f.isStopStation,
          f.miles,
          p.id,
          p.model,
          f.status,
          f.tripId
          )
          FROM FlightDatabaseEntity f
          JOIN AirportDatabaseEntity da ON da.id = f.departureAirportId
          JOIN AirportDatabaseEntity aa ON aa.id = f.arrivalAirportId
          JOIN PlaneDatabaseEntity p ON p.id = f.planeId
          WHERE f.tripId = :tripId 
          AND NOT EXISTS (
                  SELECT 1
                  FROM FlightDatabaseEntity fde
                  WHERE fde.tripId = :tripId
                  AND fde.status IN ('DELAYED', 'DEPARTED')
              )
          AND f.status = 'ON_TIME'
          """
  )
  List<FlightPlaneProjection> findAllByTripId(String tripId);

  Optional<FlightDatabaseEntity> findFirstByFlightNumber(String flightNumber);

  @Query(
      """
      select f.id from FlightDatabaseEntity f
      left join TripDatabaseEntity t ON t.id = f.tripId
      left join ReservedTripDatabaseEntity rt on rt.tripId  = t.id
      where rt.ticketId = :ticketId
      """)
  List<String> findFlightIdByTicketId(String ticketId);

  @Modifying
  @Transactional
  @Query("UPDATE FlightDatabaseEntity f SET f.status = :status WHERE f.id = :flightId")
  void updateFlightStatus(String flightId, String status);
}
