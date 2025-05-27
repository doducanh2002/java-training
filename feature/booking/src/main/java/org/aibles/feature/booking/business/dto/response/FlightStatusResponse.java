package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.entity.Flight;

import java.time.LocalDateTime;

public class FlightStatusResponse {

  private String id;
  private String flightNumber;
  private String airline;
  private AirportResponse departureAirport;
  private AirportResponse arrivalAirport;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private boolean isStopStation;
  private Long miles;
  private String status;

  public FlightStatusResponse(
      String id,
      String flightNumber,
      String airline,
      AirportResponse departureAirport,
      AirportResponse arrivalAirport,
      LocalDateTime departureTime,
      LocalDateTime arrivalTime,
      boolean isStopStation,
      Long miles,
      String status) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.airline = airline;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.isStopStation = isStopStation;
    this.miles = miles;
    this.status = status;
  }

  public FlightStatusResponse() {}

  public static FlightStatusResponse from(
      Flight flight, Airport departureAirport, Airport arrivalAirport) {
    FlightStatusResponse response = new FlightStatusResponse();
    response.setId(flight.getId());
    response.setFlightNumber(flight.getFlightNumber());
    response.setAirline(flight.getAirline());
    response.setDepartureTime(flight.getDepartureTime());
    response.setArrivalTime(flight.getArrivalTime());
    response.setStatus(flight.getStatus());
    response.setMiles(flight.getMiles());
    response.setIsStopStation(flight.getIsStopStation());
    response.setDepartureAirport(AirportResponse.from(departureAirport));
    response.setArrivalAirport(AirportResponse.from(arrivalAirport));
    return response;
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

  public AirportResponse getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(AirportResponse departureAirport) {
    this.departureAirport = departureAirport;
  }

  public AirportResponse getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(AirportResponse arrivalAirport) {
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

  public boolean getIsStopStation() {
    return isStopStation;
  }

  public void setIsStopStation(boolean stopStation) {
    isStopStation = stopStation;
  }

  public Long getMiles() {
    return miles;
  }

  public void setMiles(Long miles) {
    this.miles = miles;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
