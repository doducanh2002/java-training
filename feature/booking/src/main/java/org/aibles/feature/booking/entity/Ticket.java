package org.aibles.feature.booking.entity;

public class Ticket {

  private String id;
  private String confirmationCode;
  private String numOfPassenger;
  private String customerId;
  private String tripType;
  private Promotion promotion;

  public Ticket() {
  }

  public Ticket(String id, String confirmationCode, String numOfPassenger, String customerId,
      String tripType) {
    this.id = id;
    this.confirmationCode = confirmationCode;
    this.numOfPassenger = numOfPassenger;
    this.customerId = customerId;
    this.tripType = tripType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getConfirmationCode() {
    return confirmationCode;
  }

  public void setConfirmationCode(String confirmationCode) {
    this.confirmationCode = confirmationCode;
  }

  public String getNumOfPassenger() {
    return numOfPassenger;
  }

  public void setNumOfPassenger(String numOfPassenger) {
    this.numOfPassenger = numOfPassenger;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Promotion getPromotion() {
    return promotion;
  }

  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
  }

  public String getTripType() {
    return tripType;
  }

  public void setTripType(String tripType) {
    this.tripType = tripType;
  }
}
