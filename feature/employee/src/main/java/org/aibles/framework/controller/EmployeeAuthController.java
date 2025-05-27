package org.aibles.framework.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.business.dto.request.ChangePasswordFirstLoginUseCaseRequest;
import org.aibles.business.dto.response.LoginResponse;
import org.aibles.business.usecase.ChangePasswordFirstTimeLoginUseCase;
import org.aibles.business.usecase.LoginEmployeeUseCase;
import org.aibles.framework.controller.dto.request.ChangePasswordFirstLoginRequest;
import org.aibles.framework.controller.dto.request.LoginRequest;
import org.aibles.framework.controller.dto.response.BaseResponse;
import org.aibles.util.UserAgentUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeAuthController {

  private final LoginEmployeeUseCase loginEmployeeUseCase;
  private final ChangePasswordFirstTimeLoginUseCase changePasswordFirstTimeLoginUseCase;

  @PostMapping("/login")
  public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request,
      HttpServletRequest httpServletRequest) {
    log.info("(login)request : {}", request);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        loginEmployeeUseCase.invoke(request.getUsername(), request.getPassword(),
            UserAgentUtil.createUserAgent(httpServletRequest)));
  }

  @PostMapping("/first-login/password")
  public BaseResponse<Void> changePasswordFirstLogin(
      @RequestBody @Valid ChangePasswordFirstLoginRequest request) {
    log.info("(changePasswordFirstLogin)request : {}", request);
    ChangePasswordFirstLoginUseCaseRequest useCaseRequest = ChangePasswordFirstLoginUseCaseRequest
        .builder()
        .email(request.getEmail())
        .resetPasswordKey(request.getResetPasswordKey())
        .oldPassword(request.getOldPassword())
        .newPassword(request.getNewPassword())
        .confirmNewPassword(request.getConfirmNewPassword())
        .build();
    changePasswordFirstTimeLoginUseCase.invoke(useCaseRequest);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}