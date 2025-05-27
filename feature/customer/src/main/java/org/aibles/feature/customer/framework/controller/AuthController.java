package org.aibles.feature.customer.framework.controller;

import static org.aibles.feature.customer.business.constant.CustomerApiConstant.BaseUrl.CUSTOMERS_URL;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.customer.business.usecase.*;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.request.SignupUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.*;
import org.aibles.feature.customer.framework.controller.dto.request.*;
import org.aibles.feature.customer.framework.impl.ResendOtpFactory;
import org.aibles.feature.customer.util.UserAgentUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CUSTOMERS_URL)
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final ResendOtpFactory resendOtpFactory;
  private final ActiveAccountUseCase activeAccountUseCase;
  private final SignUpUseCase signUpUseCase;
  private final LoginUseCase loginUseCase;

  @PostMapping("/my/otp/resend")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> resendOtp(@RequestBody @Valid ResendOtpRequest request) {
    log.info("(resendOtp)email: {}, type: {}", request.getEmail(), request.getType());
    resendOtpFactory.invoke(request);
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
  }

  @PostMapping("/active")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> activeAccount(@RequestBody @Valid SendActiveAccountRequest request) {
    log.info("(activeAccount)email: {}, otp: {}", request.getEmail(), request.getOtp());
    return activeAccountUseCase.invoke(request.getEmail(), request.getOtp());
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<SignupResponse> register(@RequestBody @Valid SignupRequest request) {
    log.info("(register)request: {}", request);
    return signUpUseCase.invoke(SignupUseCaseRequest.from(request));
  }

  @PostMapping("/login")
  public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request,
      HttpServletRequest httpServletRequest) {
    log.info("(login)request : {}", request);
    var response = loginUseCase.invoke(request.getUsername(), request.getPassword(),
        UserAgentUtil.createUserAgent(httpServletRequest));
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(),
        response);
  }
}
