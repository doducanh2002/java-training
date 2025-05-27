package org.aibles.business.usecase;


import org.aibles.business.dto.request.UserAgentDTO;
import org.aibles.business.dto.response.LoginResponse;

public interface LoginEmployeeUseCase {
  LoginResponse invoke(String username, String password, UserAgentDTO userAgentDTO);
}
