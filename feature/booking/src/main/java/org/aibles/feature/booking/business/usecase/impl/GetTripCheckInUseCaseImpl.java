package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.adapter.repository.PassengerInfoRepository;
import org.aibles.feature.booking.adapter.repository.TicketRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.dto.response.TripResponse;
import org.aibles.feature.booking.business.exception.ConfirmationCodeNotFoundException;
import org.aibles.feature.booking.business.exception.PassengerInfoNotFoundException;
import org.aibles.feature.booking.business.usecase.GetTripCheckInUseCase;
import org.aibles.feature.booking.entity.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetTripCheckInUseCaseImpl implements GetTripCheckInUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      GetTripCheckInUseCaseImpl.class);
  private static final String INLAND_COUNTRY = "vietnam";
  private static final int HOURS_BEFORE_DEPARTURE = 24;
  private static final int MINUTES_BEFORE_DEPARTURE_DOMESTIC = 45;
  private static final int MINUTES_BEFORE_DEPARTURE_INTERNATIONAL = 90;

  private final TicketRepository ticketRepository;
  private final PassengerInfoRepository passengerInfoRepository;
  private final TripRepository tripRepository;
  private final AirportRepository airportRepository;

  public GetTripCheckInUseCaseImpl(TicketRepository ticketRepository,
      PassengerInfoRepository passengerInfoRepository, TripRepository tripRepository,
      AirportRepository airportRepository) {
    this.ticketRepository = ticketRepository;
    this.passengerInfoRepository = passengerInfoRepository;
    this.tripRepository = tripRepository;
    this.airportRepository = airportRepository;
  }

  @Override
  public List<TripResponse> invoke(String passengerLastName, String confirmationCode) {
    var ticket = ticketRepository.getTicketByConfirmationCode(confirmationCode)
        .orElseThrow(() -> {
          log.error("(invoke)confirmationCode: {}",  confirmationCode);
          throw new ConfirmationCodeNotFoundException(confirmationCode);
        });
    if(!passengerInfoRepository.existsByTicketIdAndLastName(ticket.getId(), passengerLastName)) {
      log.error("(invoke)ticketId: {}, passengerLastName: {}",  confirmationCode, passengerLastName);
      throw new PassengerInfoNotFoundException();
    }
    List<Trip> trips = tripRepository.findCheckInTripByTicketId (ticket.getId());
    List<TripResponse> tripResponses = new ArrayList<>();
    for (Trip trip : trips) {
      var departureCountry = airportRepository.findCountryByRegion(trip.getDeparture());
      var arrivalCountry = airportRepository.findCountryByRegion(trip.getArrival());
      LocalDateTime now = LocalDateTime.now();
      boolean canCheckIn = false;

      if (departureCountry.equals(INLAND_COUNTRY) && arrivalCountry.equals(INLAND_COUNTRY)) {
        canCheckIn = now.isAfter(trip.getStartDate().minusHours(HOURS_BEFORE_DEPARTURE)) && now.isBefore(trip.getStartDate().minusMinutes(MINUTES_BEFORE_DEPARTURE_DOMESTIC));
      } else {
        canCheckIn = now.isAfter(trip.getStartDate().minusHours(HOURS_BEFORE_DEPARTURE)) && now.isBefore(trip.getStartDate().minusMinutes(MINUTES_BEFORE_DEPARTURE_INTERNATIONAL));
      }

      if (canCheckIn) {
        tripResponses.add(TripResponse.from(trip));
      }
    }
    return tripResponses;
  }
}
