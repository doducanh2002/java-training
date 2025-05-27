package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.request.SignupUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.dto.response.SignupResponse;

public interface SignUpUseCase {

    BaseResponse<SignupResponse> invoke(SignupUseCaseRequest request);
}
