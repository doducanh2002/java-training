package org.aibles.feature.booking.entity;

public class FareBenefit {

  private String id;
  private Fare fare;
  private Benefits benefit;

  public FareBenefit() {
  }

  public FareBenefit(String id, Fare fare, Benefits benefit) {
    this.id = id;
    this.fare = fare;
    this.benefit = benefit;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Fare getFare() {
    return fare;
  }

  public void setFare(Fare fare) {
    this.fare = fare;
  }

  public Benefits getBenefit() {
    return benefit;
  }

  public void setBenefit(Benefits benefit) {
    this.benefit = benefit;
  }
}
