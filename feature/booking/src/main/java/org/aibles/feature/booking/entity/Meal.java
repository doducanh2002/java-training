package org.aibles.feature.booking.entity;

import java.time.LocalTime;
import java.util.List;

public class Meal {

  private String id;
  private String dish;
  private LocalTime mealTime;
  private Long price;
  private Flight flight;
  private List<ReservedTrip> reservedTrip;

  public Meal() {
  }

  public Meal(String id, String dish, LocalTime mealTime, Long price) {
    this.id = id;
    this.dish = dish;
    this.mealTime = mealTime;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDish() {
    return dish;
  }

  public void setDish(String dish) {
    this.dish = dish;
  }

  public LocalTime getMealTime() {
    return mealTime;
  }

  public void setMealTime(LocalTime mealTime) {
    this.mealTime = mealTime;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

  public List<ReservedTrip> getReservedTrip() {
    return reservedTrip;
  }

  public void setReservedTrip(List<ReservedTrip> reservedTrip) {
    this.reservedTrip = reservedTrip;
  }
}
