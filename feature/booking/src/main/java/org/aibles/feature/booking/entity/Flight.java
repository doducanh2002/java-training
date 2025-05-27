package org.aibles.feature.booking.entity;

import java.time.LocalDateTime;

public class Flight {

  private String id;
  private String flightNumber;
  private String airline;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private Boolean isStopStation;
  private Long miles;
  private String status;
  private Airport departureAirport;
  private Airport arrivalAirport;
  private Plane plane;
  private Trip trip;

  public Flight() {
  }

  public Flight(String id, String fightNumber, String airline, LocalDateTime departureTime, LocalDateTime arrivalTime,
      Boolean isStopStation, Long miles, String status) {
    this.id = id;
    this.flightNumber = fightNumber;
    this.airline = airline;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.isStopStation = isStopStation;
    this.miles = miles;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public Airport getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(Airport departureAirport) {
    this.departureAirport = departureAirport;
  }

  public Airport getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(Airport arrivalAirport) {
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

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public Boolean getIsStopStation() {
    return isStopStation;
  }

  public void setIsStopStation(Boolean stopStation) {
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

  public Plane getPlane() {
    return plane;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  public Trip getTrip() {
    return trip;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  @Override
  public String toString() {
    return "Flight{" +
        "id='" + id + '\'' +
        ", flightNumber='" + flightNumber + '\'' +
        ", airline='" + airline + '\'' +
        ", departureTime=" + departureTime +
        ", arrivalTime=" + arrivalTime +
        ", isStopStation=" + isStopStation +
        ", miles=" + miles +
        ", status='" + status + '\'' +
        ", departureAirport=" + departureAirport +
        ", arrivalAirport=" + arrivalAirport +
        ", plane=" + plane +
        ", trip=" + trip +
        '}';
  }
}
