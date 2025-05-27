package org.aibles.feature.customer.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aibles.feature.customer.framework.validation.ValidateEmail;

@Data
@AllArgsConstructor
public class UnlockAccountRequest {

    @NotBlank(message = "Email must not be blank")
    @ValidateEmail
    private String email;

    @NotBlank(message = "Otp must not be blank")
    private String otp;

    public UnlockAccountRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}