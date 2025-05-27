package org.aibles.feature.booking.business.dto.request.calculateprice;

import java.util.List;

public class CalculatePriceUseCaseRequest {

  private List<CalculatePriceTripUseCaseRequest> trips;

  private int numOfPassengers;

  private String promotionId;

  public CalculatePriceUseCaseRequest() {
  }

  public CalculatePriceUseCaseRequest(List<CalculatePriceTripUseCaseRequest> trips,
      int numOfPassengers, String promotionId) {
    this.trips = trips;
    this.numOfPassengers = numOfPassengers;
    this.promotionId = promotionId;
  }

  public List<CalculatePriceTripUseCaseRequest> getTrips() {
    return trips;
  }

  public void setTrips(
      List<CalculatePriceTripUseCaseRequest> trips) {
    this.trips = trips;
  }

  public int getNumOfPassengers() {
    return numOfPassengers;
  }

  public void setNumOfPassengers(int numOfPassengers) {
    this.numOfPassengers = numOfPassengers;
  }

  public String getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }
}
