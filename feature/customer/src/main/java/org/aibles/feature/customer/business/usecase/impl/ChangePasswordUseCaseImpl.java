package org.aibles.feature.customer.business.usecase.impl;

import java.util.Objects;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.feature.customer.business.exception.AuthMethodDoesNotAcceptException;
import org.aibles.feature.customer.business.usecase.ChangePasswordUseCase;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.AccountNotFoundException;
import org.aibles.feature.customer.business.exception.PasswordConfirmNotMatchException;
import org.aibles.feature.customer.business.exception.PasswordSimilarException;
import org.aibles.feature.customer.business.exception.PasswordIncorrectException;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      ChangePasswordUseCaseImpl.class);

  private final AccountRepository accountRepository;

  public ChangePasswordUseCaseImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public BaseResponse<Void> invoke(String oldPassword, String newPassword, String confirmNewPassword,
      String accountId) {
    log.info("(invoke)oldPassword: {}, newPassword: {} confirmNewPassword: {}, accountId: {}",
        oldPassword, newPassword, confirmNewPassword, accountId);

    var account = accountRepository
        .findByAccountId(accountId)
        .orElseThrow(() -> {
          log.error("(invoke)oldPassword: {}, newPassword: {} confirmNewPassword: {}, accountId: {}",
              oldPassword, newPassword, confirmNewPassword, accountId);
          throw new AuthMethodDoesNotAcceptException();
        });
    if (Objects.equals(oldPassword, newPassword)) {
      log.error(
          "(invoke)oldPassword: {}, newPassword:{} similar",
          oldPassword,
          newPassword);
      throw new PasswordSimilarException();
    }
    if (!Objects.equals(newPassword, confirmNewPassword)) {
      log.error(
          "(invoke)newPassword: {}, newPasswordConfirm:{}  don't match",
          newPassword,
          confirmNewPassword);
      throw new PasswordConfirmNotMatchException(newPassword, confirmNewPassword);
    }

    if (!EncryptUtil.getPasswordEncoder().matches(oldPassword, account.getPassword())) {
      log.error(
          "(invoke)password: {}, oldPassword:{}  incorrect password",
          account.getPassword(),
          oldPassword);
      throw new PasswordIncorrectException();
    }
    accountRepository.updatePasswordById(accountId, EncryptUtil.getPasswordEncoder().encode(newPassword));
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
  }
}
