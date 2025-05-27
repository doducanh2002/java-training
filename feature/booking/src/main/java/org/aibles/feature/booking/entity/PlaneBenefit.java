package org.aibles.feature.booking.entity;

public class PlaneBenefit {

  private String id;
  private Plane plane;
  private Benefits benefit;

  public PlaneBenefit() {
  }

  public PlaneBenefit(String id, Plane plane, Benefits benefit) {
    this.id = id;
    this.plane = plane;
    this.benefit = benefit;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Plane getPlane() {
    return plane;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  public Benefits getBenefit() {
    return benefit;
  }

  public void setBenefit(Benefits benefit) {
    this.benefit = benefit;
  }
}
