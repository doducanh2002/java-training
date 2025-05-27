package org.aibles.feature.customer.business.usecase.impl;

import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_ACTIVE_ACCOUNT_KEY;
import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_TTL_MINUTES;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.SendOtpActiveUseCase;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.util.OTPGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendOtpActiveUseCaseImpl implements SendOtpActiveUseCase {

  private static final Logger log = LoggerFactory.getLogger(SendOtpActiveUseCaseImpl.class);
  private final CustomerRepository customerRepository;
  private final RedisRepository redisRepository;
  private final EmailHelper emailHelper;

  public SendOtpActiveUseCaseImpl(CustomerRepository customerRepository, RedisRepository redisRepository, EmailHelper emailHelper) {
    this.customerRepository = customerRepository;
    this.redisRepository = redisRepository;
    this.emailHelper = emailHelper;
  }

  @Override
  public void invoke(String email) {
    if(!customerRepository.existsCustomerByEmail(email)) {
      log.error("(invoke)email: {}", email);
      throw new EmailNotFoundException(email);
    }
    String otp = OTPGenerator.generateOtp();

    var redisKey = email + OTP_ACTIVE_ACCOUNT_KEY;
    redisRepository.save(redisKey, otp, OTP_TTL_MINUTES, TimeUnit.MINUTES);
    String subject = "Your OTP for account activation";

    var param = new HashMap<String, Object>();
    param.put("otp", otp);
    param.put("otp_life", String.valueOf(OTP_TTL_MINUTES));
    emailHelper.send(subject, email, "OTP-template", param);
  }
}
