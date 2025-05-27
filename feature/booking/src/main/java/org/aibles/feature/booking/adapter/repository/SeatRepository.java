package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.Seat;

public interface SeatRepository {

  void save(Seat seat);
  void saveAll(List<Seat> seats);

  Optional<Seat> findByPlaneTravelClassAndSeatRowAndSeatColumn(
      String planeTravelClassId, int seatRow, String seatColumn);

  List<Seat> findByFareIdAndFlightId(String fareId, String flightId);
}
