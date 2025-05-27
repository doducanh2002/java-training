package org.aibles.business.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.aibles.business.dto.request.CreateEmployeeUseCaseRequest.CreateEmployeeUseCaseRequestBuilder;
import org.aibles.framework.validation.ValidatePassword;

public class ChangePasswordFirstLoginUseCaseRequest {

  private String email;
  private String resetPasswordKey;
  private String oldPassword;
  private String newPassword;
  private String confirmNewPassword;

  public ChangePasswordFirstLoginUseCaseRequest() {
  }

  public ChangePasswordFirstLoginUseCaseRequest(String email, String resetPasswordKey,
      String oldPassword,
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

  public static ChangePasswordFirstLoginUseCaseRequestBuilder builder() {
    return new ChangePasswordFirstLoginUseCaseRequestBuilder();
  }

  public static class ChangePasswordFirstLoginUseCaseRequestBuilder {

    private String email;
    private String resetPasswordKey;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public ChangePasswordFirstLoginUseCaseRequestBuilder() {
    }

    public ChangePasswordFirstLoginUseCaseRequestBuilder email(String email) {
      this.email = email;
      return this;
    }

    public ChangePasswordFirstLoginUseCaseRequestBuilder resetPasswordKey(String resetPasswordKey) {
      this.resetPasswordKey = resetPasswordKey;
      return this;
    }

    public ChangePasswordFirstLoginUseCaseRequestBuilder oldPassword(String oldPassword) {
      this.oldPassword = oldPassword;
      return this;
    }

    public ChangePasswordFirstLoginUseCaseRequestBuilder newPassword(String newPassword) {
      this.newPassword = newPassword;
      return this;
    }

    public ChangePasswordFirstLoginUseCaseRequestBuilder confirmNewPassword(
        String confirmNewPassword) {
      this.confirmNewPassword = confirmNewPassword;
      return this;
    }

    public ChangePasswordFirstLoginUseCaseRequest build() {
      return new ChangePasswordFirstLoginUseCaseRequest(email, resetPasswordKey, oldPassword,
          newPassword, confirmNewPassword);
    }
  }
}
