package org.aibles.feature.booking.entity;

public class PassengerAssistance {

  private String id;
  private PassengerInfo passengerInfo;
  private SpecialAssistance specialAssistance;

  public PassengerAssistance() {
  }

  public PassengerAssistance(String id, PassengerInfo passengerInfo,
      SpecialAssistance specialAssistance) {
    this.id = id;
    this.passengerInfo = passengerInfo;
    this.specialAssistance = specialAssistance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PassengerInfo getPassengerInfo() {
    return passengerInfo;
  }

  public void setPassengerInfo(PassengerInfo passengerInfo) {
    this.passengerInfo = passengerInfo;
  }

  public SpecialAssistance getSpecialAssistance() {
    return specialAssistance;
  }

  public void setSpecialAssistance(SpecialAssistance specialAssistance) {
    this.specialAssistance = specialAssistance;
  }
}
