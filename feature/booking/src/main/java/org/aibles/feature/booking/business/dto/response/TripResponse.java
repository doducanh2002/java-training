package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.entity.Trip;

import java.time.LocalDateTime;

public class TripResponse {

  private String id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String departure;
  private String arrival;

  public TripResponse() {
  }

  public TripResponse(String id, LocalDateTime startDate, LocalDateTime endDate, String departure, String arrival) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departure = departure;
    this.arrival = arrival;
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

  public static TripResponse from(Trip trip) {
    TripResponse response = new TripResponse();
    response.setId(trip.getId());
    response.setStartDate(trip.getStartDate());
    response.setEndDate(trip.getEndDate());
    response.setDeparture(trip.getDeparture());
    response.setArrival(trip.getArrival());
    return response;
  }
}
