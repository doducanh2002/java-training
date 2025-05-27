package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.request.UserAgentDTO;
import org.aibles.feature.customer.business.dto.response.LoginResponse;

public interface LoginUseCase {
  LoginResponse invoke(String username, String password, UserAgentDTO userAgentDTO);
}
