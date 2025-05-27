package org.aibles.feature.customer.business.usecase.impl;

import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.feature.customer.business.constant.CacheConstant;

import org.aibles.feature.customer.business.constant.OtpFailedUnlockedTime;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.dto.response.VerifyResetPasswordOtpResponse;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.VerifyResetPasswordUseCase;
import org.aibles.feature.customer.business.exception.OTPInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

public class VerifyResetPasswordUseCaseImpl implements VerifyResetPasswordUseCase {

    private static final Logger log = LoggerFactory.getLogger(VerifyResetPasswordUseCaseImpl.class);
    private final CustomerRepository customerRepository;
    private final RedisRepository redisRepository;



    public VerifyResetPasswordUseCaseImpl(CustomerRepository customerRepository, RedisRepository redisRepository) {
        this.customerRepository = customerRepository;
        this.redisRepository = redisRepository;
    }

    @Override
    public BaseResponse<VerifyResetPasswordOtpResponse> invoke(String email, String otp) {
        if (!customerRepository.existsCustomerByEmail(email)) {
            log.error("(invoke)email not found : {}", email);
            throw new EmailNotFoundException(email);
        }
        String resetPassOtpKey = email + CacheConstant.RESET_PASSWORD_OTP_KEY;
        Optional<Object> storedOtpOptional = redisRepository.get(resetPassOtpKey);
        String storedOtp = (String) storedOtpOptional.orElse(null);

        if (storedOtp == null || !storedOtp.equals(otp)) {
            log.error("(invoke) invalidOTP: {}", otp);
            handleFailedAttempt(email);
            throw new OTPInvalidException(otp);
        }
        log.info("OTP verified successfully for email: {}", email);
        redisRepository.delete(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY, email);
        redisRepository.delete(CacheConstant.FAILED_OTP_ATTEMPT_KEY, email);


        String resetPasswordKey = generateResetPasswordKey(email);

        redisRepository.save(CacheConstant.RESET_PASSWORD_KEY, email, resetPasswordKey);

        log.info("Reset password key generated and saved for email: {}", email);
        VerifyResetPasswordOtpResponse response = new VerifyResetPasswordOtpResponse(email, resetPasswordKey);

        return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), response);
    }

    private void handleFailedAttempt(String email) {
        Integer attempts = redisRepository.getOrDefault(CacheConstant.FAILED_OTP_ATTEMPT_KEY, email, 0);
        attempts++;
        redisRepository.save(CacheConstant.FAILED_OTP_ATTEMPT_KEY, email, attempts);

        if (attempts == OtpFailedUnlockedTime.FIVE.getAttempts()) {
            redisRepository.save(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY,
                    email, Instant.now().getEpochSecond() + OtpFailedUnlockedTime.FIVE.getCooldownTime());
            log.info("Account locked for 5 failed attempts for email: {}", email);
        }
        if (attempts == OtpFailedUnlockedTime.TEN.getAttempts()) {
            redisRepository.save(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY,
                    email, Instant.now().getEpochSecond() + OtpFailedUnlockedTime.TEN.getCooldownTime());
            log.info("Account locked for 10 failed attempts for email: {}", email);
        }
        if (attempts == OtpFailedUnlockedTime.FIFTEEN.getAttempts()) {
            redisRepository.save(CacheConstant.OTP_FAILED_UNLOCK_TIME_KEY,
                    email, Instant.now().getEpochSecond() + OtpFailedUnlockedTime.FIFTEEN.getCooldownTime());
            log.info("Account locked for 15 failed attempts for email: {}", email);
        }
    }

    private String generateResetPasswordKey(String email) {
        return Base64.getEncoder().encodeToString((email + System.currentTimeMillis()).getBytes());
    }
}
