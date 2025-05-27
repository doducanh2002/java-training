package org.aibles.feature.booking.business.dto.request;

import java.util.List;

public class TripPurchaseUseCaseRequest {

  private String id;
  private List<FlightPurchaseUseCaseRequest> flights;

  public TripPurchaseUseCaseRequest() {
  }

  public TripPurchaseUseCaseRequest(String id, List<FlightPurchaseUseCaseRequest> flights) {
    this.id = id;
    this.flights = flights;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<FlightPurchaseUseCaseRequest> getFlights() {
    return flights;
  }

  public void setFlights(List<FlightPurchaseUseCaseRequest> flights) {
    this.flights = flights;
  }

  public static TripPurchaseUseCaseRequest.TripPurchaseUseCaseBuilder builder() {
    return new TripPurchaseUseCaseRequest.TripPurchaseUseCaseBuilder();
  }

  public static class TripPurchaseUseCaseBuilder {

    private String id;
    private List<FlightPurchaseUseCaseRequest> flights;

    public TripPurchaseUseCaseBuilder() {
    }

    public TripPurchaseUseCaseRequest.TripPurchaseUseCaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TripPurchaseUseCaseRequest.TripPurchaseUseCaseBuilder flights(
        List<FlightPurchaseUseCaseRequest> flights) {
      this.flights = flights;
      return this;
    }

    public TripPurchaseUseCaseRequest build() {
      return new TripPurchaseUseCaseRequest(id, flights);
    }
  }
}
