package org.aibles.feature.booking.entity;

import java.time.LocalDateTime;

public class Trip {

  private String id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String departure;
  private String arrival;

  public Trip() {
  }

  public Trip(String id, LocalDateTime startDate, LocalDateTime endDate, String departure, String arrival) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departure = departure;
    this.arrival = arrival;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
}
