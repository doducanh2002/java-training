package org.aibles.feature.booking.business.dto.response;

public class ValidateSeatResponse {

  private boolean isAvailable;

  public ValidateSeatResponse() {
  }

  public ValidateSeatResponse(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(boolean isUsed) {
    this.isAvailable = isAvailable;
  }
}
