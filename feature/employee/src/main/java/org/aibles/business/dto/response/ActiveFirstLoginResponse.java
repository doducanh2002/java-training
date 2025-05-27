package org.aibles.business.dto.response;

public class ActiveFirstLoginResponse extends LoginResponse {

  private String resetPasswordKey;

  public ActiveFirstLoginResponse() {
  }

  public ActiveFirstLoginResponse(String resetPasswordKey) {
    this.resetPasswordKey = resetPasswordKey;
  }

  public String getResetPasswordKey() {
    return resetPasswordKey;
  }

  public void setResetPasswordKey(String resetPasswordKey) {
    this.resetPasswordKey = resetPasswordKey;
  }
}
