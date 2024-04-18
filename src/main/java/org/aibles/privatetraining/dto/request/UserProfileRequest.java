package org.aibles.privatetraining.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileRequest {

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotBlank(message = "Email must not be blank.")
    @Email
    private String email;
}
