package org.aibles.feature.customer.framework.controller.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.customer.framework.validation.ValidateEmail;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendOTPRequest {

    @ValidateEmail
    private String email;
}
