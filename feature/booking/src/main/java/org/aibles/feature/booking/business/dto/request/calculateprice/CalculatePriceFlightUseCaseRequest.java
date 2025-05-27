package org.aibles.feature.booking.business.dto.request.calculateprice;

public class CalculatePriceFlightUseCaseRequest {

  private String id;

  private String mealId;

  public CalculatePriceFlightUseCaseRequest() {
  }

  public CalculatePriceFlightUseCaseRequest(String id, String mealId) {
    this.id = id;
    this.mealId = mealId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMealId() {
    return mealId;
  }

  public void setMealId(String mealId) {
    this.mealId = mealId;
  }
}
