package org.aibles.privatetraining.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveOTPRequest {

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotNull(message = "OTP not null.")
    private Integer otp;
}