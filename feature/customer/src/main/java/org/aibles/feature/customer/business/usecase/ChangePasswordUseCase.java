package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.BaseResponse;

public interface ChangePasswordUseCase {

  BaseResponse<Void> invoke(String oldPassword, String newPassword, String confirmNewPassword, String accountId);
}
