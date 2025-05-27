package org.aibles.feature.booking.framework.repository;

import java.util.List;
import org.aibles.feature.booking.framework.datasource.ReservedSeatDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedSeatDatabaseEntityRepository extends
    JpaRepository<ReservedSeatDatabaseEntity, Long> {

  @Query("""
      SELECT rs.seatId
      FROM ReservedSeatDatabaseEntity rs
               JOIN ReservedTripDatabaseEntity rt ON rs.reservedTripId = rt.id
               JOIN FlightDatabaseEntity f ON rt.tripId = f.tripId
      WHERE f.departureTime >= NOW()
        AND f.id = :flightId
      """)
  List<String> getReservedSeatsByFlight(String flightId);

  @Query("""
          select rs.id from ReservedSeatDatabaseEntity rs 
          left join ReservedTripDatabaseEntity rt on rt.id = rs.reservedTripId
          where rt.ticketId = :ticketId
          """)
  List<String> getReservedSeatsByTicketId(String ticketId);
}
