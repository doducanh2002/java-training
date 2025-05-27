package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.aibles.framework.validation.ValidateEmail;
import org.aibles.framework.validation.ValidatePassword;

public class ChangePasswordFirstLoginRequest {

  @NotBlank(message = "email is required")
  @ValidateEmail
  private String email;
  @NotBlank(message = "resetPasswordKey is required")
  private String resetPasswordKey;
  @NotBlank(message = "oldPassword is required")
  @ValidatePassword
  private String oldPassword;
  @NotBlank(message = "newPassword is required")
  @ValidatePassword
  private String newPassword;
  @NotBlank(message = "confirmNewPassword is required")
  @ValidatePassword
  private String confirmNewPassword;

  public ChangePasswordFirstLoginRequest() {
  }

  public ChangePasswordFirstLoginRequest(String email, String resetPasswordKey, String oldPassword,
      String newPassword, String confirmNewPassword) {
    this.email = email;
    this.resetPasswordKey = resetPasswordKey;
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
    this.confirmNewPassword = confirmNewPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getResetPasswordKey() {
    return resetPasswordKey;
  }

  public void setResetPasswordKey(String resetPasswordKey) {
    this.resetPasswordKey = resetPasswordKey;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfirmNewPassword() {
    return confirmNewPassword;
  }

  public void setConfirmNewPassword(String confirmNewPassword) {
    this.confirmNewPassword = confirmNewPassword;
  }
}
