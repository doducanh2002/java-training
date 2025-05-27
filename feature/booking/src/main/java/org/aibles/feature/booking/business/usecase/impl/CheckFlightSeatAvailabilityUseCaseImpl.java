package org.aibles.feature.booking.business.usecase.impl;

import static org.aibles.feature.booking.constant.CacheConstant.RESERVED_SEAT_KEY;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.ReservedSeatRepository;
import org.aibles.feature.booking.business.dto.response.AvailabilitySeatResponse;
import org.aibles.feature.booking.business.exception.FlightNotFoundException;
import org.aibles.feature.booking.business.usecase.CheckFlightSeatAvailabilityUseCase;

public class CheckFlightSeatAvailabilityUseCaseImpl implements CheckFlightSeatAvailabilityUseCase {

  private final FlightRepository flightRepository;
  private final BookingCacheRepository bookingCacheRepository;
  private final ReservedSeatRepository reservedSeatRepository;

  public CheckFlightSeatAvailabilityUseCaseImpl(
      FlightRepository flightRepository,
      BookingCacheRepository bookingCacheRepository,
      ReservedSeatRepository reservedSeatRepository) {
    this.flightRepository = flightRepository;
    this.bookingCacheRepository = bookingCacheRepository;
    this.reservedSeatRepository = reservedSeatRepository;
  }

  @Override
  public AvailabilitySeatResponse invoke(String flightId, List<String> seats) {
    if (!flightRepository.existsById(flightId)) {
      throw new FlightNotFoundException(flightId);
    }
    String redisKey = RESERVED_SEAT_KEY + "_" + flightId;

    Set<String> cachedSeatIds = bookingCacheRepository.members(redisKey, String.class);
    if (cachedSeatIds.isEmpty()) {
      return new AvailabilitySeatResponse(Collections.emptyList(), true);
    }

    List<String> unavailableSeats = seats.stream()
        .filter(cachedSeatIds.stream().toList()::contains)
        .toList();

    if (!unavailableSeats.isEmpty()) {
      return new AvailabilitySeatResponse(unavailableSeats, false);
    } else {
      return new AvailabilitySeatResponse(Collections.emptyList(), true);
    }
  }
}
