package org.aibles.feature.booking.business.dto.response;

public class ValidatePromotionCodeResponse {

  private boolean isUsed;

  public ValidatePromotionCodeResponse() {
  }

  public ValidatePromotionCodeResponse(boolean isUsed) {
    this.isUsed = isUsed;
  }

  public boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(boolean isUsed) {
    this.isUsed = isUsed;
  }
}
