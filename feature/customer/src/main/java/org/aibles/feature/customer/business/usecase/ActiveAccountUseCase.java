package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.BaseResponse;

public interface ActiveAccountUseCase {

  BaseResponse<Void> invoke(String email, String otp);
}
