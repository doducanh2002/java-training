package org.aibles.feature.booking.business.dto.request.calculateprice;

import java.util.List;

public class CalculatePriceTripUseCaseRequest {

  private String id;

  private String fareId;

  private List<CalculatePriceFlightUseCaseRequest> flights;

  public CalculatePriceTripUseCaseRequest() {
  }

  public CalculatePriceTripUseCaseRequest(String id, String fareId,
      List<CalculatePriceFlightUseCaseRequest> flights) {
    this.id = id;
    this.fareId = fareId;
    this.flights = flights;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFareId() {
    return fareId;
  }

  public void setFareId(String fareId) {
    this.fareId = fareId;
  }

  public List<CalculatePriceFlightUseCaseRequest> getFlights() {
    return flights;
  }

  public void setFlights(
      List<CalculatePriceFlightUseCaseRequest> flights) {
    this.flights = flights;
  }
}
