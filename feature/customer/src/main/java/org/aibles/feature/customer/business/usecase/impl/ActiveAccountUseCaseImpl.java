package org.aibles.feature.customer.business.usecase.impl;

import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_ACTIVE_ACCOUNT_KEY;

import java.util.Objects;

import org.aibles.adapter.repository.AccountRepository;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.ActiveAccountUseCase;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.business.exception.OTPInvalidException;
import org.aibles.feature.customer.business.exception.OTPNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveAccountUseCaseImpl implements ActiveAccountUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      ActiveAccountUseCaseImpl.class);

  private final AccountRepository accountRepository;
  private final RedisRepository redisRepository;

  public ActiveAccountUseCaseImpl(AccountRepository accountRepository,
      RedisRepository redisRepository) {
    this.accountRepository = accountRepository;
    this.redisRepository = redisRepository;
  }

  @Override
  public BaseResponse<Void> invoke(String email, String otp) {
    var account = accountRepository
        .findAccountsByEmail(email)
        .orElseThrow(() -> {
          log.error("(invoke)email: {} not found", email);
          throw new EmailNotFoundException(email);
        });

    var redisKey = email + OTP_ACTIVE_ACCOUNT_KEY;
    var otpCacheOptional = redisRepository.get(redisKey);
    if (otpCacheOptional.isEmpty()) {
      log.error("(invoke)otpCache is null for email: {}", email);
      throw new OTPNotFoundException(email);
    }
    var otpCache = String.valueOf(otpCacheOptional.get());
    if (!Objects.equals(otpCache, otp)) {
      log.error("(invoke)otp : {}, otpCache : {}", otp, otpCache);
      throw new OTPInvalidException(otp);
    }

    account.setActivated(true);
    accountRepository.save(account);
    redisRepository.delete(redisKey);
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
  }
}
