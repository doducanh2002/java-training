package org.aibles.feature.customer.business.usecase.impl;

import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_TTL_MINUTES;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.SendOtpUnlockAccountUseCase;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.util.OTPGenerator;

@Slf4j
public class SendOtpUnlockAccountUseCaseImpl implements SendOtpUnlockAccountUseCase {

    public static final String UNLOCK_ACCOUNT = "UNLOCK_ACCOUNT";
    public static final String OTP = "OTP";

    private final RedisRepository redisRepository;
    private final EmailHelper emailHelper;
    private final CustomerRepository customerRepository;

    public SendOtpUnlockAccountUseCaseImpl(RedisRepository redisRepository, CustomerRepository customerRepository, EmailHelper emailHelper) {
        this.redisRepository = redisRepository;
        this.customerRepository = customerRepository;
        this.emailHelper = emailHelper;
    }


    @Override
    public BaseResponse<Void> invoke(String email) {
        if(!customerRepository.existsCustomerByEmail(email)) {
            log.error("(sendOTPUnlockAccount)email: {}", email);
            throw new EmailNotFoundException(email);
        }
        String otp = OTPGenerator.generateOtp();
        var redisKey = email + "_" + OTP + "_" + UNLOCK_ACCOUNT;
        redisRepository.save(redisKey, otp, OTP_TTL_MINUTES, TimeUnit.MINUTES);
        String subject = "Your OTP for account activation";

        var param = new HashMap<String, Object>();
        param.put("otp", otp);
        param.put("otp_life", String.valueOf(OTP_TTL_MINUTES));
        emailHelper.send(subject, email, "OTP-template", param);
        return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
    }
}
