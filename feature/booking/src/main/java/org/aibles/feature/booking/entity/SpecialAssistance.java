package org.aibles.feature.booking.entity;

import java.util.List;

public class SpecialAssistance {

  private String id;
  private String name;
  private String description;
  private List<PassengerInfo> passengerInfos;

  public SpecialAssistance() {
  }

  public SpecialAssistance(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public SpecialAssistance(String id) {
    this.id = id;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<PassengerInfo> getPassengerInfos() {
    return passengerInfos;
  }

  public void setPassengerInfos(
      List<PassengerInfo> passengerInfos) {
    this.passengerInfos = passengerInfos;
  }
}
