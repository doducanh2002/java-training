package org.aibles.feature.booking.util;

import java.util.List;
import java.util.stream.Collectors;
import org.aibles.feature.booking.business.dto.request.FlightPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.PassengerInfoPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.TripPurchaseUseCaseRequest;
import org.aibles.feature.booking.framework.controller.dto.request.FlightPurchaseRequest;
import org.aibles.feature.booking.framework.controller.dto.request.PassengerInfoPurchaseRequest;
import org.aibles.feature.booking.framework.controller.dto.request.TripPurchaseRequest;

public class PurchaseMapping {

  public static TripPurchaseUseCaseRequest convertToTripPurchaseUseCaseRequest(
      TripPurchaseRequest tripPurchaseRequest) {
    List<FlightPurchaseUseCaseRequest> flightPurchaseUseCaseRequests = tripPurchaseRequest.getFlights().stream()
        .map(PurchaseMapping::convertToFlightPurchaseUseCaseRequest)
        .collect(Collectors.toList());

    return TripPurchaseUseCaseRequest.builder()
        .id(tripPurchaseRequest.getId())
        .flights(flightPurchaseUseCaseRequests)
        .build();
  }

  public static FlightPurchaseUseCaseRequest convertToFlightPurchaseUseCaseRequest(
      FlightPurchaseRequest flightPurchaseRequest) {
    return FlightPurchaseUseCaseRequest.builder()
        .id(flightPurchaseRequest.getId())
        .fareId(flightPurchaseRequest.getFareId())
        .mealId(flightPurchaseRequest.getMealId())
        .seats(flightPurchaseRequest.getSeats())
        .build();
  }

  public static PassengerInfoPurchaseUseCaseRequest convertToPassengerInfoPurchaseUseCaseRequest(
      PassengerInfoPurchaseRequest passengerInfoPurchaseRequest) {
    return PassengerInfoPurchaseUseCaseRequest.builder()
        .num(passengerInfoPurchaseRequest.getNum())
        .firstName(passengerInfoPurchaseRequest.getFirstName())
        .middleName(passengerInfoPurchaseRequest.getMiddleName())
        .lastName(passengerInfoPurchaseRequest.getLastName())
        .country(passengerInfoPurchaseRequest.getCountry())
        .gender(passengerInfoPurchaseRequest.getGender())
        .dob(passengerInfoPurchaseRequest.getDob())
        .build();
  }
}
