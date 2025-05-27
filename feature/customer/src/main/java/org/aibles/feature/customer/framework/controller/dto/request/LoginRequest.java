package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.feature.customer.framework.validation.ValidatePassword;

@Data
public class LoginRequest {
  @NotBlank(message = "Username is required")
  private String username;
  @NotBlank(message = "Password is required")
  @ValidatePassword
  private String password;
}
