package org.aibles.feature.booking.entity;

import java.util.List;

public class Fare {

  private String id;
  private String name;
  private Long price;
  private List<Benefits> benefits;
  private TravelClass travelClass;

  public Fare() {
  }

  public Fare(String id, String name, Long price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public TravelClass getTravelClass() {
    return travelClass;
  }

  public void setTravelClass(TravelClass travelClass) {
    this.travelClass = travelClass;
  }

  public List<Benefits> getBenefits() {
    return benefits;
  }

  public void setBenefits(List<Benefits> benefits) {
    this.benefits = benefits;
  }
}
