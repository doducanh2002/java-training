package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class FlightPurchaseRequest {

  @NotBlank(message = "id is required")
  private String id;
  @NotBlank(message = "fareId is required")
  private String fareId;
  @NotNull(message = "seats are required")
  private List<String> seats;
  private String mealId;

  public FlightPurchaseRequest() {
  }

  public FlightPurchaseRequest(String id, String fareId, List<String> seats, String mealId) {
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
}
