package org.aibles.feature.booking.business.dto.request;

import java.time.LocalDate;

public class OneWayFilterUseCaseRequest {
  private String departureAirportId;
  private String arrivalAirportId;
  private LocalDate date;

  public OneWayFilterUseCaseRequest() {
  }

  public OneWayFilterUseCaseRequest(String departureAirportId, String arrivalAirportId, LocalDate date) {
    this.departureAirportId = departureAirportId;
    this.arrivalAirportId = arrivalAirportId;
    this.date = date;
  }

  public String getDepartureAirportId() {
    return departureAirportId;
  }

  public void setDepartureAirportId(String departureAirportId) {
    this.departureAirportId = departureAirportId;
  }

  public String getArrivalAirportId() {
    return arrivalAirportId;
  }

  public void setArrivalAirportId(String arrivalAirportId) {
    this.arrivalAirportId = arrivalAirportId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
