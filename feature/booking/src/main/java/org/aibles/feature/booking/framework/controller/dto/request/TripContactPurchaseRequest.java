package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.aibles.feature.booking.framework.validation.ValidateEmail;
import org.aibles.feature.booking.framework.validation.ValidatePhone;

public class TripContactPurchaseRequest {

  @ValidateEmail
  @NotBlank(message = "primaryEmail is required")
  private String primaryEmail;
  @ValidateEmail
  @NotBlank(message = "secondaryEmail is required")
  private String secondaryEmail;
  @ValidatePhone
  @NotBlank(message = "phoneNumber is required")
  private String phoneNumber;

  public TripContactPurchaseRequest() {
  }

  public TripContactPurchaseRequest(String primaryEmail, String secondaryEmail,
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
}
