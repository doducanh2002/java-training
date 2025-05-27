package org.aibles.feature.customer.business.usecase.impl;

import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Account;
import org.aibles.entity.Customer;
import org.aibles.feature.customer.adapter.repository.CustomerAccountRepository;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.UnlockAccountUseCase;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.AccountNotFoundException;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.business.exception.OTPInvalidException;
import org.aibles.feature.customer.business.exception.OTPNotFoundException;
import org.aibles.feature.customer.entity.CustomerAccount;
import org.aibles.feature.customer.framework.controller.dto.request.UnlockAccountRequest;

@Slf4j
public class UnlockAccountUseCaseImpl implements UnlockAccountUseCase {

  public static final String UNLOCK_ACCOUNT = "UNLOCK_ACCOUNT";
  public static final String OTP = "OTP";

  private final AccountRepository accountRepository;
  private final CustomerAccountRepository customerAccountRepository;
  private final RedisRepository redisRepository;


  private final CustomerRepository customerRepository;

  public UnlockAccountUseCaseImpl(AccountRepository accountRepository,
      CustomerAccountRepository customerAccountRepository, RedisRepository redisRepository,
      CustomerRepository customerRepository) {
    this.accountRepository = accountRepository;
    this.customerAccountRepository = customerAccountRepository;
    this.redisRepository = redisRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public BaseResponse<Void> invoke(UnlockAccountRequest request) {
    customerRepository.findCustomerByEmail(request.getEmail())
        .orElseThrow(() -> new EmailNotFoundException(request.getEmail()));
    var redisKey = request.getEmail() + "_" + OTP + "_" + UNLOCK_ACCOUNT;
    var otpCacheOptional = redisRepository.get(redisKey);
    if (otpCacheOptional.isEmpty()) {
      log.error("(unlockAccount)otpCache is null for email: {}", request.getEmail());
      throw new OTPNotFoundException(request.getEmail());
    }
    var otpCache = String.valueOf(otpCacheOptional.get());
    if (!Objects.equals(otpCache, request.getOtp())) {
      log.error("(unlockAccount)otp : {}, otpCache : {}", request.getOtp(), otpCache);
      throw new OTPInvalidException(request.getOtp());
    }

    updateIsLockPermanentByEmail(request.getEmail());
    return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
  }

  public void updateIsLockPermanentByEmail(String email) {
    Customer customer = customerRepository.findCustomerByEmail(email)
        .orElseThrow(() -> new EmailNotFoundException(email));
    String customerId = customer.getId();
    List<CustomerAccount> customerAccounts = customerAccountRepository.findByCustomerId(customerId);
    for (CustomerAccount customerAccount : customerAccounts) {
      String accountId = customerAccount.getAccountId();
      Account account = accountRepository.findByAccountId(accountId)
          .orElseThrow(() -> new AccountNotFoundException(accountId));
      account.setLockPermanent(false);
      accountRepository.save(account);
    }
  }
}
