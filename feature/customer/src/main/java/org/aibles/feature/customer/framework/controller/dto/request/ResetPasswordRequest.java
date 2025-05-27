package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.customer.framework.validation.ValidateEmail;
import org.aibles.feature.customer.framework.validation.ValidatePassword;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "Email is required")
    @ValidateEmail
    private String email;

    @NotBlank(message = "ResetPasswordKey is required")
    private String resetPasswordKey;

    @NotBlank(message = "NewPassword is required")
    @ValidatePassword
    private String newPassword;

    @NotBlank(message = "ConfirmPassword is required")
    @ValidatePassword
    private String confirmPassword;
}
