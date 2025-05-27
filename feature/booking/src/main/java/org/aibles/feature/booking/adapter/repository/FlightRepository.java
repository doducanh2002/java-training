package org.aibles.feature.booking.adapter.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.framework.datasource.FlightDatabaseEntity;

public interface FlightRepository {
  List<Flight> findAll(String from, String to, LocalDate date);

  List<Flight> findDetailByTripId(String tripId);

  boolean existsById(String id);

  void saveAll(List<Flight> flightList);

  Optional<Flight> findByFlightNumber(String flightNumber);

  List<String> findFlightIdByTicketId(String ticketId);

  Optional<FlightDatabaseEntity> findByFlightId(String flightId);

  void updateFlightStatus(String flightId, String status);
}
