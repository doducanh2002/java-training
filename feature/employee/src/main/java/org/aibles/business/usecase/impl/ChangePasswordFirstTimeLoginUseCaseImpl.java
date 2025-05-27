package org.aibles.business.usecase.impl;

import java.util.Objects;
import java.util.Optional;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.RedisCacheRepository;
import org.aibles.business.constant.CacheConstant;
import org.aibles.business.dto.request.ChangePasswordFirstLoginUseCaseRequest;
import org.aibles.business.exception.AccountByEmailNotFoundException;
import org.aibles.business.exception.InvalidResetPasswordKeyException;
import org.aibles.business.exception.PasswordConfirmNotMatchException;
import org.aibles.business.exception.PasswordIncorrectException;
import org.aibles.business.exception.PasswordSimilarException;
import org.aibles.business.usecase.ChangePasswordFirstTimeLoginUseCase;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangePasswordFirstTimeLoginUseCaseImpl implements
    ChangePasswordFirstTimeLoginUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      ChangePasswordFirstTimeLoginUseCaseImpl.class);

  private final AccountRepository accountRepository;
  private final EmployeeRepository employeeRepository;
  private final RedisCacheRepository redisCacheRepository;

  public ChangePasswordFirstTimeLoginUseCaseImpl(AccountRepository accountRepository,
      EmployeeRepository employeeRepository, RedisCacheRepository redisCacheRepository) {
    this.accountRepository = accountRepository;
    this.employeeRepository = employeeRepository;
    this.redisCacheRepository = redisCacheRepository;
  }

  @Override
  public void invoke(ChangePasswordFirstLoginUseCaseRequest request) {
    if (!Objects.equals(request.getNewPassword(), request.getConfirmNewPassword())) {
      log.error(
          "(invoke)newPassword: {}, newPasswordConfirm:{}  don't match",
          request.getNewPassword(),
          request.getConfirmNewPassword());
      throw new PasswordConfirmNotMatchException();
    }
    if (Objects.equals(request.getOldPassword(), request.getNewPassword())) {
      log.error(
          "(invoke)oldPassword: {}, newPassword:{} similar",
          request.getOldPassword(),
          request.getNewPassword());
      throw new PasswordSimilarException();
    }

    var account = accountRepository.findAccountByEmailEmployee(request.getEmail())
        .orElseThrow(() -> {
          log.error(
              "(invoke)email: {}, resetPasswordKey: {}, oldPassword: {}, newPassword: {} confirmNewPassword: {}",
              request.getEmail(), request.getResetPasswordKey(), request.getOldPassword(),
              request.getNewPassword(), request.getConfirmNewPassword());
          throw new AccountByEmailNotFoundException(request.getEmail());
        });
    if (!EncryptUtil.getPasswordEncoder()
        .matches(request.getOldPassword(), account.getPassword())) {
      log.error(
          "(invoke)password: {}, oldPassword:{}  incorrect password",
          account.getPassword(),
          request.getOldPassword());
      throw new PasswordIncorrectException();
    }

    Optional<Object> resetPasswordKeyOptional = redisCacheRepository.get(
        CacheConstant.EMPLOYEE_RESET_PASSWORD_KEY, request.getEmail());
    String resetPasswordKey = (String) resetPasswordKeyOptional.orElse(null);

    log.info("(resetPasswordKey)resetPasswordKey: {}", resetPasswordKey);

    if (resetPasswordKey == null || !Objects.equals(resetPasswordKey,
        request.getResetPasswordKey())) {
      log.error("(invoke)Invalid reset password key : {} ", request.getResetPasswordKey());
      throw new InvalidResetPasswordKeyException(request.getResetPasswordKey());
    }

    redisCacheRepository.delete(CacheConstant.EMPLOYEE_RESET_PASSWORD_KEY, request.getEmail());

    accountRepository.updatePasswordByEmailEmployee(request.getEmail(),
        EncryptUtil.getPasswordEncoder().encode(request.getNewPassword()));
    employeeRepository.updateIsFirstLoginByEmail(request.getEmail(), false);
  }
}
