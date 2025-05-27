package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.framework.controller.dto.request.UnlockAccountRequest;

public interface UnlockAccountUseCase {

    BaseResponse<Void> invoke(UnlockAccountRequest request);
}
