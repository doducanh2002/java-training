package org.aibles.bussiness.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ImportTripDTO {

  private String id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String departure;
  private String arrival;
  private String flightNumber;
  private String airline;
  private String departureAirportCode;
  private String arrivalAirportCode;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private Integer isStopStation;
  private Long miles;
  private String status;
  private String plane;
  private String dish;
  private LocalTime mealTime;
  private Long price;
  private String flights;
  private String meal;

  public ImportTripDTO() {
  }

  public ImportTripDTO(String id, LocalDateTime startDate, LocalDateTime endDate, String departure,
      String arrival,String flightNumber, String airline, String departureAirportCode,
      String arrivalAirportCode, LocalDateTime departureTime, LocalDateTime arrivalTime,
      Integer isStopStation, Long miles, String status, String plane, String dish,
      LocalTime mealTime, Long price, String flights, String meal) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departure = departure;
    this.arrival = arrival;
    this.flightNumber = flightNumber;
    this.airline = airline;
    this.departureAirportCode = departureAirportCode;
    this.arrivalAirportCode = arrivalAirportCode;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.isStopStation = isStopStation;
    this.miles = miles;
    this.status = status;
    this.plane = plane;
    this.dish = dish;
    this.mealTime = mealTime;
    this.price = price;
    this.flights = flights;
    this.meal = meal;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
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

  public String getDepartureAirportCode() {
    return departureAirportCode;
  }

  public void setDepartureAirportCode(String departureAirportCode) {
    this.departureAirportCode = departureAirportCode;
  }

  public String getArrivalAirportCode() {
    return arrivalAirportCode;
  }

  public void setArrivalAirportCode(String arrivalAirportCode) {
    this.arrivalAirportCode = arrivalAirportCode;
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

  public Integer getIsStopStation() {
    return isStopStation;
  }

  public void setIsStopStation(Integer isStopStation) {
    this.isStopStation = isStopStation;
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

  public String getPlane() {
    return plane;
  }

  public void setPlane(String plane) {
    this.plane = plane;
  }

  public String getDish() {
    return dish;
  }

  public void setDish(String dish) {
    this.dish = dish;
  }

  public LocalTime getMealTime() {
    return mealTime;
  }

  public void setMealTime(LocalTime mealTime) {
    this.mealTime = mealTime;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getFlights() {
    return flights;
  }

  public void setFlights(String flights) {
    this.flights = flights;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }
}
