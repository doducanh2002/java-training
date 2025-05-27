package org.aibles.feature.booking.business.dto.response.filtertrips;

import java.time.LocalDateTime;
import java.util.List;

public class FilterTripsResponse {
  private String id;
  private String departure;
  private String arrival;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private List<FilterTripsFlightResponse> flights;
  private List<FilterTripsTravelClassResponse> travelClasses;

  public FilterTripsResponse() {
  }

  public FilterTripsResponse(String id, String departure, String arrival,
      LocalDateTime departureTime,
      LocalDateTime arrivalTime) {
    this.id = id;
    this.departure = departure;
    this.arrival = arrival;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    this.arrival = arrival;
  }

  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(LocalDateTime departureTime) {
    this.departureTime = departureTime;
  }

  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(LocalDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public List<FilterTripsFlightResponse> getFlights() {
    return flights;
  }

  public void setFlights(
      List<FilterTripsFlightResponse> flights) {
    this.flights = flights;
  }

  public List<FilterTripsTravelClassResponse> getTravelClasses() {
    return travelClasses;
  }

  public void setTravelClasses(
      List<FilterTripsTravelClassResponse> travelClasses) {
    this.travelClasses = travelClasses;
  }
}
