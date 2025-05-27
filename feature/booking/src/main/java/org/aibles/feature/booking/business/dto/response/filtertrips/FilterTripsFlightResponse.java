package org.aibles.feature.booking.business.dto.response.filtertrips;

import java.time.LocalDateTime;

public class FilterTripsFlightResponse {
  private String id;
  private String flightNumber;
  private String airline;
  private boolean isStopStation;
  private String departureAirport;
  private String arrivalAirport;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private FilterTripsPlaneResponse plane;

  public FilterTripsFlightResponse() {
  }

  public FilterTripsFlightResponse(String id, String flightNumber, String airline,
      boolean isStopStation, String departureAirport, String arrivalAirport,
      LocalDateTime departureTime, LocalDateTime arrivalTime, FilterTripsPlaneResponse plane) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.airline = airline;
    this.isStopStation = isStopStation;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.plane = plane;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public boolean isStopStation() {
    return isStopStation;
  }

  public void setStopStation(boolean stopStation) {
    isStopStation = stopStation;
  }

  public String getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  public String getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
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

  public FilterTripsPlaneResponse getPlane() {
    return plane;
  }

  public void setPlane(
      FilterTripsPlaneResponse plane) {
    this.plane = plane;
  }
}
