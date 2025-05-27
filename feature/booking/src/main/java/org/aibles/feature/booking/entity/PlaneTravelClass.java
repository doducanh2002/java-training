package org.aibles.feature.booking.entity;

public class PlaneTravelClass {

  private String id;
  private Plane plane;
  private TravelClass travelClass;
  private int capacity;

  public PlaneTravelClass() {
  }

  public PlaneTravelClass(String id) {
    this.id = id;
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

  public TravelClass getTravelClass() {
    return travelClass;
  }

  public void setTravelClass(TravelClass travelClass) {
    this.travelClass = travelClass;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
