package org.aibles.feature.booking.adapter.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.Trip;

public interface TripRepository {

  List<Trip> findCheckInTripByTicketId(String ticketId);

  List<Trip> findAllByAccountId(String accountId);

  List<Trip> findAllByAirportIdAndStartDate(String departureAirportId, String arrivalAirportId,
      LocalDate startDate);

  Trip save(Trip trip);

  void saveAll(List<Trip> tripList);

  boolean existsById(String id);

  Optional<Trip> findById(String id);
}
