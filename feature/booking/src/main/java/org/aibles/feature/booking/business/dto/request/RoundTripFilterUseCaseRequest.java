package org.aibles.feature.booking.business.dto.request;

import java.time.LocalDate;
import org.aibles.feature.booking.constant.RoundTripDirection;

public class RoundTripFilterUseCaseRequest {
  private String departureAirportId;
  private String arrivalAirportId;
  private LocalDate departureDate;
  private LocalDate arrivalDate;
  private RoundTripDirection direction;

  public RoundTripFilterUseCaseRequest() {
  }

  public RoundTripFilterUseCaseRequest(String departureAirportId, String arrivalAirportId,
      LocalDate departureDate, LocalDate arrivalDate, RoundTripDirection direction) {
    this.departureAirportId = departureAirportId;
    this.arrivalAirportId = arrivalAirportId;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.direction = direction;
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

  public LocalDate getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(LocalDate departureDate) {
    this.departureDate = departureDate;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public RoundTripDirection getDirection() {
    return direction;
  }

  public void setDirection(RoundTripDirection direction) {
    this.direction = direction;
  }
}
