package org.aibles.feature.booking.business.usecase.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.business.dto.response.FlightStatusResponse;
import org.aibles.feature.booking.business.exception.AirportNotFoundException;
import org.aibles.feature.booking.business.usecase.GetFlightStatusUsecase;
import org.aibles.feature.booking.entity.Flight;

public class GetFlightStatusUsecaseImpl implements GetFlightStatusUsecase {
  private final FlightRepository flightRepository;
  private final AirportRepository airportRepository;

  public GetFlightStatusUsecaseImpl(
      FlightRepository flightRepository, AirportRepository airportRepository) {
    this.flightRepository = flightRepository;
    this.airportRepository = airportRepository;
  }

  @Override
  public List<FlightStatusResponse> invoke(String from, String to, LocalDate date) {
    if (!airportRepository.existByAirportId(from)) {
      throw new AirportNotFoundException(from);
    }
    if (!airportRepository.existByAirportId(to)) {
      throw new AirportNotFoundException(to);
    }

    List<Flight> flights = flightRepository.findAll(from, to, date);
    List<FlightStatusResponse> responses = new ArrayList<>();

    for (Flight flight : flights) {
      FlightStatusResponse flightStatusResponse =
          FlightStatusResponse.from(
              flight, flight.getDepartureAirport(), flight.getArrivalAirport());
      responses.add(flightStatusResponse);
    }
    return responses;
  }
}
