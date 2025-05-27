package org.aibles.business.usecase.impl;

import java.time.Instant;
import java.time.LocalDateTime;

import java.util.Base64;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.LoginDataRepository;
import org.aibles.adapter.repository.RedisCacheRepository;
import org.aibles.business.constant.AccountLockedTime;
import org.aibles.business.constant.CacheConstant;
import org.aibles.business.dto.request.UserAgentDTO;
import org.aibles.business.dto.response.ActiveFirstLoginResponse;
import org.aibles.business.dto.response.ActiveLoginResponse;
import org.aibles.business.dto.response.LoginResponse;
import org.aibles.business.dto.response.UnactiveLoginResponse;
import org.aibles.business.exception.AccountLockedException;
import org.aibles.business.exception.EmployeeIsDeletedException;
import org.aibles.business.exception.EmployeeNotFoundException;
import org.aibles.business.exception.PasswordIncorrectException;
import org.aibles.business.exception.UsernameNotFoundException;
import org.aibles.business.usecase.LoginEmployeeUseCase;
import org.aibles.entity.Account;
import org.aibles.entity.LoginData;

import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEmployeeUseCaseImpl implements LoginEmployeeUseCase {

  private static final Logger log = LoggerFactory.getLogger(LoginEmployeeUseCaseImpl.class);

  private final AccountRepository accountRepository;

  private final LoginDataRepository loginDataRepository;

  private final EmployeeRepository employeeRepository;

  private final AuthenticationHelper authenticationHelper;

  private final RedisCacheRepository redisCacheRepository;

  public LoginEmployeeUseCaseImpl(AccountRepository accountRepository,
      LoginDataRepository loginDataRepository, EmployeeRepository employeeRepository,
      AuthenticationHelper authenticationHelper,
      RedisCacheRepository redisCacheRepository) {
    this.accountRepository = accountRepository;
    this.loginDataRepository = loginDataRepository;
    this.employeeRepository = employeeRepository;
    this.authenticationHelper = authenticationHelper;
    this.redisCacheRepository = redisCacheRepository;
  }

  @Override
  public LoginResponse invoke(String username, String password, UserAgentDTO userAgentDTO) {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> {
          log.error("(invoke)username {} not found", username);
          return new UsernameNotFoundException(username);
        });

    if (Boolean.TRUE.equals(account.getLockPermanent())) {
      log.error("(invoke)account has username : {} is locked permanently", account.getUsername());
      throw new AccountLockedException();
    }

    if (Boolean.FALSE.equals(account.getActivated())) {
      log.warn("(invoke)account has username : {} is not actived", account.getUsername());
      return new UnactiveLoginResponse("Your account is not activated");
    }

    var employee = employeeRepository.findByAccountId(account.getId())
        .orElseThrow(() -> {
          log.error("(invoke)accountId : {} not found", account.getId());
          return new EmployeeNotFoundException();
        });

    if(Boolean.TRUE.equals(employee.getDeleted())) {
      log.error("(invoke)employee is deleted");
      throw new EmployeeIsDeletedException();
    }

    Long unlockLoginTime = redisCacheRepository.getOrDefault(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
        employee.getEmail(), 0L);
    if (Instant.now().getEpochSecond() < unlockLoginTime) {
      log.error("(invoke)account has username : {} is locked temporary", account.getUsername());
      throw new AccountLockedException();
    }
    if (!EncryptUtil.getPasswordEncoder().matches(password, account.getPassword())) {
      loginDataRepository.save(createLoginData(userAgentDTO, account.getId(), false));
      handleFailedAttempt(employee.getEmail(), account.getId());
      log.error("(invoke)account has username : {} is have wrong password", account.getUsername());
      throw new PasswordIncorrectException();
    }

    redisCacheRepository.delete(CacheConstant.LOGIN_UNLOCK_TIME_KEY, employee.getEmail());
    redisCacheRepository.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, employee.getEmail());

    loginDataRepository.save(createLoginData(userAgentDTO, account.getId(), true));

    if (Boolean.TRUE.equals(employee.getFirstLogin())) {
      log.info("(invoke)response the first login");
      String resetPasswordKey = generateResetPasswordKey(employee.getEmail());
      redisCacheRepository.save(CacheConstant.EMPLOYEE_RESET_PASSWORD_KEY, employee.getEmail(), resetPasswordKey);
      ActiveFirstLoginResponse activeFirstLoginResponse = new ActiveFirstLoginResponse();
      activeFirstLoginResponse.setResetPasswordKey(resetPasswordKey);
      return activeFirstLoginResponse;
    } else {
      log.info("(invoke)response login");
      ActiveLoginResponse loginResponse = new ActiveLoginResponse();
      loginResponse.setAccessToken(authenticationHelper.generateAccessToken(account.getUsername(),
          employee.getEmail()));
      loginResponse.setRefreshToken(authenticationHelper.generateRefreshToken(account.getUsername(),
          employee.getEmail()));
      loginResponse.setAccessTokenLifeTime(authenticationHelper.getAccessTokenLifeTime());
      loginResponse.setRefreshTokenLifeTime(authenticationHelper.getAccessTokenLifeTime());
      return loginResponse;
    }
  }

  private void handleFailedAttempt(String email, String accountId) {
    Integer attempts = redisCacheRepository.getOrDefault(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email,
        0);
    attempts++;
    redisCacheRepository.save(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email, attempts);

    if (attempts.equals(AccountLockedTime.FIVE.getAttempts())) {
      redisCacheRepository.save(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.FIVE.getCooldownTime());
      log.info("Account locked for 5 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.TEN.getAttempts())) {
      redisCacheRepository.save(CacheConstant.LOGIN_UNLOCK_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.TEN.getCooldownTime());
      log.info("Account locked for 10 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.FIFTEEN.getAttempts())) {
      accountRepository.updateLockPermanentById(accountId, true);
      redisCacheRepository.delete(CacheConstant.LOGIN_UNLOCK_TIME_KEY, email);
      redisCacheRepository.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email);
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

  private String generateResetPasswordKey(String email) {
    return Base64.getEncoder().encodeToString((email + System.currentTimeMillis()).getBytes());
  }
}
