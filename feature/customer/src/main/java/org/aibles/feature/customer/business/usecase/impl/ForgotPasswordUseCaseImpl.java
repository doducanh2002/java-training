package org.aibles.feature.customer.business.usecase.impl;

import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.entity.Account;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.exception.AuthMethodDoesNotAcceptException;
import org.aibles.feature.customer.business.usecase.ForgotPasswordUseCase;
import org.aibles.feature.customer.business.constant.CacheConstant;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.AccountLockedException;
import org.aibles.feature.customer.business.exception.EmailDeliveryException;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.util.OTPGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_TTL_MINUTES;

public class ForgotPasswordUseCaseImpl implements ForgotPasswordUseCase {

    private static final Logger log = LoggerFactory.getLogger(ForgotPasswordUseCaseImpl.class);

    private final CustomerRepository customerRepository;

    private final RedisRepository redisRepository;

    private final EmailHelper mailHelper;
    private final AccountRepository accountRepository;

    public ForgotPasswordUseCaseImpl(CustomerRepository customerRepository,
                                     RedisRepository redisRepository,
                                     EmailHelper mailHelper, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.redisRepository = redisRepository;
        this.mailHelper = mailHelper;
        this.accountRepository = accountRepository;
    }

    @Override
    public BaseResponse<Void> invoke(String email) {
        log.info("(invoke) email: {}", email);

        String username = accountRepository.findAccountsByEmail(email)
            .map(Account::getUsername)
            .orElseThrow(AuthMethodDoesNotAcceptException::new);

        if (!customerRepository.existsCustomerByEmail(email)) {
            log.error("(invoke)email not found : {}", email);
            throw new EmailNotFoundException(email);
        }
        long lockedTime = redisRepository.getOrDefault(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY, email, 0L);
        if (Instant.now().getEpochSecond() < lockedTime) {
            log.error("(invoke)lockedTime : {}", lockedTime);
            throw new AccountLockedException(username);
        }
        redisRepository.delete(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY, email);

        String otp = OTPGenerator.generateOtp();
        redisRepository.save(email + CacheConstant.RESET_PASSWORD_OTP_KEY, otp,
                CacheConstant.OTP_TTL_MINUTES, TimeUnit.MINUTES);
        final String OTP_PASSWORD_RESET_SUBJECT = "Your OTP for Password Reset";
        final String OTP_TEMPLATE = "OTP-template";
        var param = new HashMap<String, Object>();
        param.put("otp", otp);
        param.put("otp_life", String.valueOf(OTP_TTL_MINUTES));
        try {
            mailHelper.send(OTP_PASSWORD_RESET_SUBJECT, email, OTP_TEMPLATE, param);
        } catch (Exception e) {
            throw new EmailDeliveryException();
        }
        return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
    }
}