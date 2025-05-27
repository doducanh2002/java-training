package org.aibles.feature.customer.business.usecase.impl;

import java.time.Instant;
import java.time.LocalDateTime;

import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Account;
import org.aibles.adapter.repository.LoginDataRepository;
import org.aibles.entity.Customer;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.LoginUseCase;
import org.aibles.feature.customer.business.constant.AccountLockedTime;
import org.aibles.feature.customer.business.constant.CacheConstant;
import org.aibles.feature.customer.business.dto.request.UserAgentDTO;
import org.aibles.feature.customer.business.dto.response.ActiveLoginResponse;
import org.aibles.feature.customer.business.dto.response.LoginResponse;
import org.aibles.feature.customer.business.dto.response.UnactiveLoginResponse;
import org.aibles.feature.customer.business.exception.AccountLockedException;
import org.aibles.feature.customer.business.exception.CustomerNotFoundException;
import org.aibles.feature.customer.business.exception.PasswordIncorrectException;
import org.aibles.feature.customer.business.exception.UsernameNotFoundException;
import org.aibles.entity.LoginData;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginUseCaseImpl implements LoginUseCase {

  private static final Logger log = LoggerFactory.getLogger(LoginUseCaseImpl.class);

  private final AccountRepository accountRepository;

  private final LoginDataRepository loginDataRepository;

  private final CustomerRepository customerRepository;

  private final AuthenticationHelper authenticationHelper;

  private final RedisRepository redisRepository;

  public LoginUseCaseImpl(AccountRepository accountRepository,
      LoginDataRepository loginDataRepository, CustomerRepository customerRepository,
      AuthenticationHelper authenticationHelper,
      RedisRepository redisRepository) {
    this.accountRepository = accountRepository;
    this.loginDataRepository = loginDataRepository;
    this.customerRepository = customerRepository;
    this.authenticationHelper = authenticationHelper;
    this.redisRepository = redisRepository;
  }

  @Override
  public LoginResponse invoke(String username, String password, UserAgentDTO userAgentDTO) {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> {
          log.error("Customer have username {} not found", username);
          return new UsernameNotFoundException(username);
        });

    if (Boolean.TRUE.equals(account.getLockPermanent())) {
      log.error("(invoke)account has username : {} is locked permanently", account.getUsername());
      throw new AccountLockedException(username);
    }

    if (Boolean.FALSE.equals(account.getActivated())) {
      log.warn("(invoke)account has username : {} is not actived", account.getUsername());
      return new UnactiveLoginResponse("Your account is not activated");
    }

    Customer customer = customerRepository.findCustomerByAccountId(account.getId())
        .orElseThrow(() -> {
          log.error("Customer have account id : {} not found", account.getId());
          return new CustomerNotFoundException(account.getId());
        });

    Long unlockLoginTime = redisRepository.getOrDefault(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
        customer.getEmail(), 0L);
    if (Instant.now().getEpochSecond() < unlockLoginTime) {
      log.error("(invoke)account has username : {} is locked temporary", account.getUsername());
      throw new AccountLockedException(username);
    }
    if (!EncryptUtil.getPasswordEncoder().matches(password, account.getPassword())) {
      loginDataRepository.save(createLoginData(userAgentDTO, account.getId(), false));
      handleFailedAttempt(customer.getEmail(), account.getId());
      log.error("(invoke)account has username : {} is have wrong password", account.getUsername());
      throw new PasswordIncorrectException();
    }

    redisRepository.delete(CacheConstant.LOGIN_UNLOCK_TIME_KEY, customer.getEmail());
    redisRepository.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, customer.getEmail());

    loginDataRepository.save(createLoginData(userAgentDTO, account.getId(), true));

    ActiveLoginResponse loginResponse = new ActiveLoginResponse();
    loginResponse.setAccessToken(authenticationHelper.generateAccessToken(account.getUsername(),
        customer.getEmail()));
    loginResponse.setRefreshToken(authenticationHelper.generateRefreshToken(account.getUsername(),
        customer.getEmail()));
    loginResponse.setAccessTokenLifeTime(authenticationHelper.getAccessTokenLifeTime());
    loginResponse.setRefreshTokenLifeTime(authenticationHelper.getAccessTokenLifeTime());

    return loginResponse;
  }

  private void handleFailedAttempt(String email, String accountId) {
    Integer attempts = redisRepository.getOrDefault(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email,
        0);
    attempts++;
    redisRepository.save(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email, attempts);

    if (attempts.equals(AccountLockedTime.FIVE.getAttempts())) {
      redisRepository.save(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.FIVE.getCooldownTime());
      log.info("Account locked for 5 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.TEN.getAttempts())) {
      redisRepository.save(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.TEN.getCooldownTime());
      log.info("Account locked for 10 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.FIFTEEN.getAttempts())) {
      accountRepository.updateLockPermanentById(accountId, true);
      redisRepository.delete(CacheConstant.LOGIN_UNLOCK_TIME_KEY, email);
      redisRepository.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email);
      log.info("Account locked for 15 failed attempts for email: {}", email);
    }
    log.info("(handleFailedAttempt)Customer have email : {} have {} failed attempts", email,
        attempts);
  }

  private LoginData createLoginData(UserAgentDTO userAgentDTO, String accountId,
      boolean isSuccess) {
    LoginData loginData = UserAgentDTO.toLoginData(userAgentDTO);
    loginData.setAccountId(accountId);
    loginData.setLoginTime(LocalDateTime.now());
    loginData.setSuccess(isSuccess);
    return loginData;
  }
}
