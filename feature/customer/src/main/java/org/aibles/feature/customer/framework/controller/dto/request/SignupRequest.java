package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.aibles.feature.customer.framework.validation.ValidateEmail;
import org.aibles.feature.customer.framework.validation.ValidatePassword;

public class SignupRequest {

  @NotBlank(message = "Email is required")
  @ValidateEmail
  private String email;
  @NotBlank(message = "Username is required")
  private String username;
  @NotBlank(message = "Password is required")
  @ValidatePassword
  private String password;
  @NotBlank(message = "Confirm Password is required")
  @ValidatePassword
  private String confirmPassword;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
