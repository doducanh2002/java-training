package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.customer.framework.validation.ValidatePassword;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordRequest {

  @NotBlank(message = "oldPassword is required")
  @ValidatePassword
  private String oldPassword;
  @NotBlank(message = "newPassword is required")
  @ValidatePassword
  private String newPassword;
  @NotBlank(message = "confirmNewPassword is required")
  @ValidatePassword
  private String confirmNewPassword;
}
