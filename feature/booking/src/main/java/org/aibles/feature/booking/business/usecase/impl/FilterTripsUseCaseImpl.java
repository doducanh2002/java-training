package org.aibles.feature.booking.business.usecase.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.dto.response.BenefitResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsFareResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsFlightResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsPlaneResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsTravelClassResponse;
import org.aibles.feature.booking.business.usecase.FilterTripsUseCase;
import org.aibles.feature.booking.constant.FlightStatusConstant;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.entity.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterTripsUseCaseImpl implements FilterTripsUseCase {

  private final Logger log = LoggerFactory.getLogger(FilterTripsUseCaseImpl.class);

  private final TripRepository tripRepository;

  private final FlightRepository flightRepository;

  private final TravelClassRepository travelClassRepository;

  public FilterTripsUseCaseImpl(TripRepository tripRepository, FlightRepository flightRepository,
      TravelClassRepository travelClassRepository) {
    this.tripRepository = tripRepository;
    this.flightRepository = flightRepository;
    this.travelClassRepository = travelClassRepository;
  }

  @Override
  public List<FilterTripsResponse> invoke(String departureAirportId, String arrivalAirportId,
      LocalDate date) {
    List<Trip> trips = tripRepository.
        findAllByAirportIdAndStartDate(departureAirportId, arrivalAirportId, date);
    List<FilterTripsResponse> filterTripsResponses = new ArrayList<>();
    String firstFlightPlaneId;
    for (Trip trip : trips) {
      FilterTripsResponse filterTripsResponse = new FilterTripsResponse();
      filterTripsResponse.setId(trip.getId());
      filterTripsResponse.setArrival(trip.getArrival());
      filterTripsResponse.setDeparture(trip.getDeparture());
      filterTripsResponse.setDepartureTime(trip.getStartDate());
      filterTripsResponse.setArrivalTime(trip.getEndDate());
      List<Flight> flights = flightRepository.findDetailByTripId(trip.getId()).stream()
          .filter(flight -> FlightStatusConstant.ON_TIME.equalsIgnoreCase(flight.getStatus()))
          .toList();
      if (flights.isEmpty()) {
        log.warn("(invoke) No ON_TIME flights found for trip: {}", trip.getId());
        continue;
      }
      List<FilterTripsFlightResponse> flightResponses = new ArrayList<>();
      firstFlightPlaneId = flights.get(0).getPlane().getId();
      for (Flight flight : flights) {
        log.info("(invoke)start add flight: {} for trip", flight);
        FilterTripsFlightResponse filterTripsFlightResponse = new FilterTripsFlightResponse();
        filterTripsFlightResponse.setId(flight.getId());
        filterTripsFlightResponse.setFlightNumber(flight.getFlightNumber());
        filterTripsFlightResponse.setAirline(flight.getAirline());
        filterTripsFlightResponse.setStopStation(flight.getIsStopStation());
        filterTripsFlightResponse.setDepartureAirport(flight.getDepartureAirport().getName());
        filterTripsFlightResponse.setArrivalAirport(flight.getArrivalAirport().getName());
        filterTripsFlightResponse.setDepartureTime(flight.getDepartureTime());
        filterTripsFlightResponse.setArrivalTime(flight.getArrivalTime());
        FilterTripsPlaneResponse filterTripsPlaneResponse = new FilterTripsPlaneResponse();
        filterTripsPlaneResponse.setId(flight.getPlane().getId());
        filterTripsPlaneResponse.setModel(flight.getPlane().getModel());
        filterTripsPlaneResponse.setBenfits(
            flight.getPlane().getBenefits().stream().map(BenefitResponse::from).toList()
        );
        filterTripsFlightResponse.setPlane(filterTripsPlaneResponse);
        flightResponses.add(filterTripsFlightResponse);
        log.info("(invoke)end add flight: {} for trip", flight);
      }
      filterTripsResponse.setFlights(flightResponses);
      List<TravelClass> travelClasses = travelClassRepository.findDetailByPlaneId(
          firstFlightPlaneId);
      List<FilterTripsTravelClassResponse> filterTripsTravelClassResponses = new ArrayList<>();
      for (TravelClass travelClass : travelClasses) {
        log.info("(invoke)start add travel class: {} for trip", travelClass);
        FilterTripsTravelClassResponse filterTripsTravelClassResponse =
            new FilterTripsTravelClassResponse();
        filterTripsTravelClassResponse.setId(travelClass.getId());
        filterTripsTravelClassResponse.setName(travelClass.getName());
        List<FilterTripsFareResponse> filterTripsFareResponses = new ArrayList<>();
        for (Fare fare: travelClass.getFares()) {
          FilterTripsFareResponse filterTripsFareResponse = new FilterTripsFareResponse();
          filterTripsFareResponse.setId(fare.getId());
          filterTripsFareResponse.setName(fare.getName());
          filterTripsFareResponse.setPrice(fare.getPrice());
          List<BenefitResponse> benefitResponses = new ArrayList<>();
          for (Benefits benefits: fare.getBenefits()) {
            BenefitResponse benefitResponse = BenefitResponse.from(benefits);
            benefitResponse.setId(benefits.getId());
            benefitResponse.setName(benefits.getName());
            benefitResponses.add(benefitResponse);
          }
          filterTripsFareResponse.setBenefits(benefitResponses);
          filterTripsFareResponses.add(filterTripsFareResponse);
        }
        filterTripsTravelClassResponse.setFares(filterTripsFareResponses);
        filterTripsTravelClassResponses.add(filterTripsTravelClassResponse);
        log.info("(invoke)end add travel class: {} for trip", travelClass);
      }
      filterTripsResponse.setTravelClasses(filterTripsTravelClassResponses);
      filterTripsResponses.add(filterTripsResponse);
    }
    return filterTripsResponses;
  }
}
