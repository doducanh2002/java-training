package org.aibles.feature.booking.business.dto.request;

import java.util.List;

public class FlightPurchaseUseCaseRequest {

  private String id;
  private String fareId;
  private List<String> seats;
  private String mealId;

  public FlightPurchaseUseCaseRequest() {
  }

  public FlightPurchaseUseCaseRequest(String id, String fareId, List<String> seats, String mealId) {
    this.id = id;
    this.fareId = fareId;
    this.seats = seats;
    this.mealId = mealId;
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

  public List<String> getSeats() {
    return seats;
  }

  public void setSeats(List<String> seats) {
    this.seats = seats;
  }

  public String getMealId() {
    return mealId;
  }

  public void setMealId(String mealId) {
    this.mealId = mealId;
  }

  public static FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder builder() {
    return new FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder();
  }

  public static class FlightPurchaseUseCaseBuilder {

    private String id;
    private String fareId;
    private List<String> seats;
    private String mealId;

    public FlightPurchaseUseCaseBuilder() {
    }

    public FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder fareId(String fareId) {
      this.fareId = fareId;
      return this;
    }

    public FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder seats(List<String> seats) {
      this.seats = seats;
      return this;
    }

    public FlightPurchaseUseCaseRequest.FlightPurchaseUseCaseBuilder mealId(String mealId) {
      this.mealId = mealId;
      return this;
    }

    public FlightPurchaseUseCaseRequest build() {
      return new FlightPurchaseUseCaseRequest(id, fareId, seats, mealId);
    }
  }
}
