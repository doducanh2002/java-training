package org.aibles.feature.booking.business.usecase.impl;

import static org.aibles.feature.booking.constant.CacheConstant.RESERVED_SEAT_KEY;

import java.util.List;
import java.util.Set;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.SeatRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.dto.response.ValidateSeatResponse;
import org.aibles.feature.booking.business.exception.FareNotFoundException;
import org.aibles.feature.booking.business.exception.FlightNotFoundException;
import org.aibles.feature.booking.business.exception.TripNotFoundException;
import org.aibles.feature.booking.business.usecase.ValidateSeatUseCase;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.entity.Seat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateSeatUseCaseImpl implements ValidateSeatUseCase {

  private static final Logger log = LoggerFactory.getLogger(ValidateSeatUseCaseImpl.class);
  private final FlightRepository flightRepository;
  private final TripRepository tripRepository;
  private final SeatRepository seatRepository;
  private final BookingCacheRepository bookingCacheRepository;
  private final FareRepository fareRepository;

  public ValidateSeatUseCaseImpl(FlightRepository flightRepository, TripRepository tripRepository,
      SeatRepository seatRepository,
      BookingCacheRepository bookingCacheRepository, FareRepository fareRepository) {
    this.flightRepository = flightRepository;
    this.tripRepository = tripRepository;
    this.seatRepository = seatRepository;
    this.bookingCacheRepository = bookingCacheRepository;
    this.fareRepository = fareRepository;
  }

  @Override
  public ValidateSeatResponse invoke(String tripId, String fareId) {

    if (!tripRepository.existsById(tripId)) {
      throw new TripNotFoundException(tripId);
    }

    if (!fareRepository.existsById(fareId)) {
      throw new FareNotFoundException(fareId);
    }

    List<Flight> flights = flightRepository.findDetailByTripId(tripId);
    boolean foundAvailableSeat = false;

    for (Flight flight : flights) {
      String flightId = flight.getId();
      log.info("Flight ID: {}", flightId);

      List<Seat> availableSeats = seatRepository.findByFareIdAndFlightId(fareId, flightId);
      Set<String> reservedSeatIds = bookingCacheRepository
          .members(RESERVED_SEAT_KEY + "_" + flightId, String.class);

      log.info("Available Seats: {}", availableSeats);

      boolean isAvailable = availableSeats.stream()
          .anyMatch(seat -> !reservedSeatIds.contains(seat.getId()));

      if (!isAvailable) {
        return new ValidateSeatResponse(false);
      }

      foundAvailableSeat = true;
    }

    return new ValidateSeatResponse(foundAvailableSeat);
  }


}
