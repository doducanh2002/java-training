
package org.aibles.feature.customer.framework.controller;


import static org.aibles.util.SecurityUtil.getCredential;

import jakarta.validation.Valid;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.request.RefreshAccessTokenUseCaseRequest;
import org.aibles.feature.customer.business.dto.request.ResetPasswordUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.CustomerDetailResponse;
import org.aibles.feature.customer.business.dto.response.RefreshAccessTokenUseCaseResponse;
import org.aibles.feature.customer.business.usecase.*;
import org.aibles.feature.customer.framework.controller.dto.request.*;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.framework.controller.dto.response.RefreshAccessTokenResponse;
import org.aibles.feature.customer.business.dto.response.VerifyResetPasswordOtpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.aibles.feature.customer.framework.controller.dto.request.UnlockAccountRequest;
import org.springframework.validation.annotation.Validated;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
  private final VerifyResetPasswordUseCase verifyResetPasswordUseCase;
  private final ChangePasswordUseCase changePasswordUseCase;
  private final UnlockAccountUseCase unlockAccountUseCase;
  private final ResetPasswordUseCase resetPasswordUseCase;
  private final ForgotPasswordUseCase forgotPasswordUseCase;
  private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;
  private final SendOtpUnlockAccountUseCase sendOtpUnlockAccountUseCase;
  private final CustomerDetailUseCase customerDetailUseCase;


  public CustomerController(VerifyResetPasswordUseCase verifyResetPasswordUseCase,
      ChangePasswordUseCase changePasswordUseCase,
      UnlockAccountUseCase unlockAccountUseCase,
      ResetPasswordUseCase resetPasswordUseCase,
      ForgotPasswordUseCase forgotPasswordUseCase,
      RefreshAccessTokenUseCase refreshAccessTokenUseCase,
      SendOtpUnlockAccountUseCase sendOtpUnlockAccountUseCase,
      CustomerDetailUseCase customerDetailUseCase) {
    this.verifyResetPasswordUseCase = verifyResetPasswordUseCase;
    this.changePasswordUseCase = changePasswordUseCase;
    this.unlockAccountUseCase = unlockAccountUseCase;
    this.forgotPasswordUseCase = forgotPasswordUseCase;
    this.resetPasswordUseCase = resetPasswordUseCase;
    this.refreshAccessTokenUseCase = refreshAccessTokenUseCase;
    this.sendOtpUnlockAccountUseCase = sendOtpUnlockAccountUseCase;
    this.customerDetailUseCase = customerDetailUseCase;
  }

  @PostMapping("/unlock-account")
  public BaseResponse<Void> unlockAccount(@RequestBody @Validated UnlockAccountRequest request) {
    return unlockAccountUseCase.invoke(request);
  }

  @PostMapping("/forgot-password")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> forgotPassword(
      @RequestBody @Valid ForgotPasswordDto forgotPasswordDto) {
    return forgotPasswordUseCase.invoke(forgotPasswordDto.getEmail());
  }

  @PostMapping("/reset-pass/otp/validation")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<VerifyResetPasswordOtpResponse> verifyResetPassword(
      @RequestBody @Valid VerifyResetPasswordOtpRequest verifyResetPasswordDto) {
    return verifyResetPasswordUseCase.invoke(verifyResetPasswordDto.getEmail(),
        verifyResetPasswordDto.getOtp());
  }

  @PostMapping("/change-password")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
    log.info("(changePassword)oldPassword: {}, newPassword: {}, confirmNewPassword: {}",
        request.getOldPassword(), request.getNewPassword(), request.getConfirmNewPassword());
    return changePasswordUseCase.invoke(request.getOldPassword(), request.getNewPassword(),
        request.getConfirmNewPassword(), getCredential());
  }

  @PostMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
    log.info("(resetPassword)email : {}, resetKey : {}", request.getEmail(),
        request.getResetPasswordKey());
    ResetPasswordUseCaseRequest useCaseRequest = ResetPasswordUseCaseRequest
        .builder()
        .email(request.getEmail())
        .resetPasswordKey(request.getResetPasswordKey())
        .newPassword(request.getNewPassword())
        .confirmPassword(request.getConfirmPassword())
        .build();
    return resetPasswordUseCase.invoke(useCaseRequest);
  }

  @PostMapping("/refresh-access-token")
  public BaseResponse<RefreshAccessTokenResponse> refreshAccessToken(
      @RequestBody @Valid RefreshAccessTokenRequest refreshAccessTokenRequest) {
    RefreshAccessTokenUseCaseResponse ucResponse = refreshAccessTokenUseCase.invoke(
        RefreshAccessTokenUseCaseRequest.from(refreshAccessTokenRequest));
    RefreshAccessTokenResponse response = RefreshAccessTokenResponse.from(ucResponse);
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(),
        response);
  }

  @PostMapping("/my/otp/unlock")
  public BaseResponse<Void> sendOTPUnlockAccount(@RequestBody @Validated SendOTPRequest request) {
    return sendOtpUnlockAccountUseCase.invoke(request.getEmail());
  }
  @GetMapping("/self")
  public BaseResponse<CustomerDetailResponse> getCustomerDetail() {
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(),
        customerDetailUseCase.invoke(getCredential()));
  }
}