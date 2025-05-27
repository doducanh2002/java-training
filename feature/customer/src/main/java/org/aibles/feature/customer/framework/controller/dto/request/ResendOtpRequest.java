package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.customer.framework.validation.ValidateEmail;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResendOtpRequest {

  @NotBlank(message = "Email is required")
  @ValidateEmail
  private String email;

  @Pattern(regexp = "ACTIVE_ACCOUNT|FORGOT_PASSWORD|UNLOCK_ACCOUNT")
  private String type;
}
