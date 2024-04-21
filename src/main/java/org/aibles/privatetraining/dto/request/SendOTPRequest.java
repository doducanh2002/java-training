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
public class SendOTPRequest {

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotBlank(message = "Email must not be blank.")
    @Email
    private String email;
}
