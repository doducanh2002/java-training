package org.aibles.feature.booking.entity;

public class ReservedMeal {

  private String id;
  private ReservedTrip reservedTrip;
  private Meal meal;
  private Long price;

  public ReservedMeal() {
  }

  public ReservedMeal(String id, ReservedTrip reservedTrip, Meal meal, Long price) {
    this.id = id;
    this.reservedTrip = reservedTrip;
    this.meal = meal;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReservedTrip getReservedTrip() {
    return reservedTrip;
  }

  public void setReservedTrip(ReservedTrip reservedTrip) {
    this.reservedTrip = reservedTrip;
  }

  public Meal getMeal() {
    return meal;
  }

  public void setMeal(Meal meal) {
    this.meal = meal;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
