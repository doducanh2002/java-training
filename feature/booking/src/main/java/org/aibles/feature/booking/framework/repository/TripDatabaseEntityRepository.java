package org.aibles.feature.booking.framework.repository;

import java.time.LocalDate;
import java.util.List;
import org.aibles.feature.booking.framework.datasource.TripDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDatabaseEntityRepository extends JpaRepository<TripDatabaseEntity, String> {

  @Query(value = """
      SELECT c FROM TripDatabaseEntity c
      JOIN ReservedTripDatabaseEntity rt ON rt.tripId = c.id
      JOIN TicketDatabaseEntity tk ON tk.id = rt.ticketId
      WHERE tk.id = :ticketId
      """)
  List<TripDatabaseEntity> findByTicketId(String ticketId);

  @Query(value = """
      SELECT t FROM TripDatabaseEntity t
      JOIN ReservedTripDatabaseEntity rt ON rt.tripId = t.id
      JOIN TicketDatabaseEntity tk ON tk.id = rt.ticketId
      JOIN CustomerAccountDatabaseEntity ca ON ca.customerId = tk.customerId
      WHERE ca.accountId = :accountId
      """)
  List<TripDatabaseEntity> getAllByAccountId(String accountId);

  @Query(value = """
        WITH FirstFlight AS (
            SELECT
                trip_id,
                departure_airport_id,
                MIN(departure_time) AS first_start_time
            FROM
                flight
            GROUP BY
                trip_id, departure_airport_id
        ),
        LastFlight AS (
            SELECT
                trip_id,
                arrival_airport_id,
                MAX(departure_time) AS last_end_time
            FROM
                flight
            GROUP BY
                trip_id, arrival_airport_id
        )
        SELECT t.*
        FROM trip t
        JOIN FirstFlight ff ON t.id = ff.trip_id
        JOIN LastFlight lf ON t.id = lf.trip_id
        WHERE ff.departure_airport_id = :departureAirportId
        AND lf.arrival_airport_id = :arrivalAirportId
        AND DATE(t.start_date) = :startDate
        """, nativeQuery = true)
  List<TripDatabaseEntity> findAllByAirportIdAndStartDate(
      @Param("departureAirportId") String departureAirportId,
      @Param("arrivalAirportId") String arrivalAirportId,
      @Param("startDate") LocalDate startDate);
}
