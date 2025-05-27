package org.aibles.feature.customer.framework.configuration;

import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.adapter.repository.LoginDataRepository;
import org.aibles.feature.customer.adapter.repository.*;
import org.aibles.feature.customer.business.usecase.impl.*;
import org.aibles.feature.customer.business.usecase.*;
import org.aibles.framework.configuration.EnableCoreEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCoreEmail
public class CustomerConfiguration {

  @Bean
  public ForgotPasswordUseCase forgotPasswordUseCase(
      CustomerRepository customerRepository,
      RedisRepository redisRepository,
      EmailHelper mailHelper,
      AccountRepository accountRepository) {
    return new ForgotPasswordUseCaseImpl(customerRepository, redisRepository, mailHelper, accountRepository);
  }

  @Bean
  public VerifyResetPasswordUseCase verifyResetPasswordUseCase(
      CustomerRepository customerRepository,
      RedisRepository redisRepository) {
    return new VerifyResetPasswordUseCaseImpl(customerRepository, redisRepository);
  }

  @Bean
  public SendOtpActiveUseCase sendOtpActiveUseCase(CustomerRepository customerRepository,
      RedisRepository redisRepository, EmailHelper emailHelper) {
    return new SendOtpActiveUseCaseImpl(customerRepository, redisRepository, emailHelper);
  }

  @Bean
  public UnlockAccountUseCase unlockAccountUseCase(AccountRepository accountRepository,
      CustomerAccountRepository customerAccountRepository,
      CustomerRepository customerRepository,
      RedisRepository redisRepository) {
    return new UnlockAccountUseCaseImpl(accountRepository,
        customerAccountRepository, redisRepository,
        customerRepository);
  }

  @Bean
  public ChangePasswordUseCase changePasswordUseCase(AccountRepository accountRepository) {
    return new ChangePasswordUseCaseImpl(accountRepository);
  }

  @Bean
  public ResetPasswordUseCase resetPasswordUseCase(CustomerRepository customerRepository,
      RedisRepository redisRepository,
      AccountRepository accountRepository) {
    return new ResetPasswordUseCaseImpl(customerRepository, accountRepository, redisRepository);
  }

  @Bean
  public ActiveAccountUseCase activeAccountUseCase(AccountRepository accountRepository,
      RedisRepository redisRepository) {
    return new ActiveAccountUseCaseImpl(accountRepository, redisRepository);
  }

  @Bean
  SignUpUseCase signUpUseCase(CustomerRepository customerRepository,
      AccountRepository accountRepository,
      CustomerAccountRepository customerAccountRepository,
      RedisRepository redisRepository, EmailHelper emailHelper) {
    return new SignUpUseCaseImpl(customerRepository, accountRepository, customerAccountRepository,
        redisRepository, emailHelper);
  }

  @Bean
  public LoginUseCase loginUseCase(AccountRepository accountRepository,
      LoginDataRepository loginDataRepository,
      CustomerRepository customerRepository, AuthenticationHelper authenticationHelper,
      RedisRepository redisRepository) {
    return new LoginUseCaseImpl(accountRepository, loginDataRepository, customerRepository,
        authenticationHelper, redisRepository);
  }

  @Bean
  public SendOtpUnlockAccountUseCase sendOtpUnlockAccountUseCase(RedisRepository redisRepository,
      CustomerRepository customerRepository, EmailHelper emailHelper) {
    return new SendOtpUnlockAccountUseCaseImpl(redisRepository, customerRepository, emailHelper);
  }

  @Bean
  public CustomerDetailUseCase customerDetailUseCase(CustomerRepository customerRepository) {
    return new CustomerDetailUseCaseImpl(customerRepository);
  }
}
