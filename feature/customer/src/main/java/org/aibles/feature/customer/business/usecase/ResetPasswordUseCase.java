package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.request.ResetPasswordUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.BaseResponse;

public interface ResetPasswordUseCase {

    BaseResponse<Void> invoke(ResetPasswordUseCaseRequest request);
}
