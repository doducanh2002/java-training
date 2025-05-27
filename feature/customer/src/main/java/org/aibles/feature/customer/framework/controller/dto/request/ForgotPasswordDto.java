package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.customer.framework.validation.ValidateEmail;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgotPasswordDto {

    @NotBlank(message = "Email is required")
    @ValidateEmail
    private String email;
}
