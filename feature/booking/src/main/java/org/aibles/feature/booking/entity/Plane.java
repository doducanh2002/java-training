package org.aibles.feature.booking.entity;

import java.util.List;

public class Plane {

  private String id;
  private String model;
  private String description;
  private List<Benefits> benefits;
  public Plane() {
  }

  public Plane(String id, String model, String description) {
    this.id = id;
    this.model = model;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Benefits> getBenefits() {
    return benefits;
  }

  public void setBenefits(List<Benefits> benefits) {
    this.benefits = benefits;
  }
}
