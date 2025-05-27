package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserved_meal")
public class ReservedMealEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String reservedTripId;

  private String mealId;

  private Long price;

  public ReservedMealEntity() {
  }

  public ReservedMealEntity(String id, String reservedTripId, String mealId, Long price) {
    this.id = id;
    this.reservedTripId = reservedTripId;
    this.mealId = mealId;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getReservedTripId() {
    return reservedTripId;
  }

  public void setReservedTripId(String reservedTripId) {
    this.reservedTripId = reservedTripId;
  }

  public String getMealId() {
    return mealId;
  }

  public void setMealId(String mealId) {
    this.mealId = mealId;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
