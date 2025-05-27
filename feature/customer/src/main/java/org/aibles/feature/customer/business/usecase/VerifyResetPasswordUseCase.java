package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.dto.response.VerifyResetPasswordOtpResponse;

public interface VerifyResetPasswordUseCase {
    BaseResponse<VerifyResetPasswordOtpResponse> invoke(String email, String otp);
}
