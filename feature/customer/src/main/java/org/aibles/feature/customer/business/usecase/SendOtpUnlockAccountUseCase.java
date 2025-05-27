package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.BaseResponse;

public interface SendOtpUnlockAccountUseCase {

    BaseResponse<Void> invoke(String email);
}
