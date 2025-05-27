package org.aibles.feature.booking.business.dto.request;

public class TripContactPurchaseUseCaseRequest {

  private String primaryEmail;
  private String secondaryEmail;
  private String phoneNumber;

  public TripContactPurchaseUseCaseRequest() {
  }

  public TripContactPurchaseUseCaseRequest(String primaryEmail, String secondaryEmail,
      String phoneNumber) {
    this.primaryEmail = primaryEmail;
    this.secondaryEmail = secondaryEmail;
    this.phoneNumber = phoneNumber;
  }

  public String getPrimaryEmail() {
    return primaryEmail;
  }

  public void setPrimaryEmail(String primaryEmail) {
    this.primaryEmail = primaryEmail;
  }

  public String getSecondaryEmail() {
    return secondaryEmail;
  }

  public void setSecondaryEmail(String secondaryEmail) {
    this.secondaryEmail = secondaryEmail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public static TripContactPurchaseUseCaseRequest.TripContactPurchaseUseCaseBuilder builder() {
    return new TripContactPurchaseUseCaseRequest.TripContactPurchaseUseCaseBuilder();
  }

  public static class TripContactPurchaseUseCaseBuilder {

    private String primaryEmail;
    private String secondaryEmail;
    private String phoneNumber;

    public TripContactPurchaseUseCaseBuilder() {
    }

    public TripContactPurchaseUseCaseRequest.TripContactPurchaseUseCaseBuilder primaryEmail(
        String primaryEmail) {
      this.primaryEmail = primaryEmail;
      return this;
    }

    public TripContactPurchaseUseCaseRequest.TripContactPurchaseUseCaseBuilder secondaryEmail(
        String secondaryEmail) {
      this.secondaryEmail = secondaryEmail;
      return this;
    }

    public TripContactPurchaseUseCaseRequest.TripContactPurchaseUseCaseBuilder phoneNumber(
        String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public TripContactPurchaseUseCaseRequest build() {
      return new TripContactPurchaseUseCaseRequest(primaryEmail, secondaryEmail, phoneNumber);
    }
  }
}
