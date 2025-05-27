package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class TripPurchaseRequest {

  @NotBlank(message = "id is required")
  private String id;
  @NotNull(message = "flights are required")
  @Valid
  private List<FlightPurchaseRequest> flights;

  public TripPurchaseRequest() {
  }

  public TripPurchaseRequest(String id, List<FlightPurchaseRequest> flights) {
    this.id = id;
    this.flights = flights;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<FlightPurchaseRequest> getFlights() {
    return flights;
  }

  public void setFlights(
      List<FlightPurchaseRequest> flights) {
    this.flights = flights;
  }
}
