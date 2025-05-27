package org.aibles.feature.customer.business.usecase.impl;

import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.exception.AuthMethodDoesNotAcceptException;
import org.aibles.feature.customer.business.usecase.ResetPasswordUseCase;
import org.aibles.feature.customer.business.constant.CacheConstant;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.request.ResetPasswordUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.exception.EmailNotFoundException;
import org.aibles.feature.customer.business.exception.InvalidResetPasswordKeyException;
import org.aibles.feature.customer.business.exception.PasswordConfirmNotMatchException;
import org.aibles.feature.customer.business.exception.PasswordSimilarException;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public class ResetPasswordUseCaseImpl implements ResetPasswordUseCase {

    private final Logger log = LoggerFactory.getLogger(ResetPasswordUseCaseImpl.class);

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    private final RedisRepository redisRepository;

    public ResetPasswordUseCaseImpl(CustomerRepository customerRepository, AccountRepository accountRepository, RedisRepository redisRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.redisRepository = redisRepository;
    }

    @Override
    public BaseResponse<Void> invoke(ResetPasswordUseCaseRequest request) {
        log.info("(invoke)request : {}", request);
        if (accountRepository.findAccountsByEmail(request.getEmail()).isEmpty()) {
            throw new EmailNotFoundException(request.getEmail());
        }

        if (!Objects.equals(request.getNewPassword(), request.getConfirmPassword())) {
            log.error("(invoke)request password does not match confirm password");
            throw new PasswordConfirmNotMatchException(request.getNewPassword(),
                request.getConfirmPassword());
        }

        Optional<Object> resetPasswordKeyOptional = redisRepository.get(CacheConstant.RESET_PASSWORD_KEY, request.getEmail());
        String resetPasswordKey = (String) resetPasswordKeyOptional.orElse(null);
        if(!customerRepository.existsCustomerByEmail(request.getEmail())) {
            log.error("(invoke)email: {}", request.getEmail());
            throw new EmailNotFoundException(request.getEmail());
        }

        String oldPassword = accountRepository.findPasswordByEmail(request.getEmail());
        if (EncryptUtil.getPasswordEncoder().matches(request.getNewPassword(), oldPassword)) {
            log.error(
                "(invoke)oldPassword: {}, newPassword:{} similar",
                oldPassword,
                request.getNewPassword());
            throw new PasswordSimilarException();
        }

        if (resetPasswordKey == null || !Objects.equals(resetPasswordKey, request.getResetPasswordKey())) {
            log.error("(invoke)Invalid reset password key : {} ", request.getResetPasswordKey());
            throw new InvalidResetPasswordKeyException(request.getResetPasswordKey());
        }

        String encryptedNewPassword = EncryptUtil.getPasswordEncoder().encode(request.getNewPassword());
        accountRepository.updatePasswordByEmail(request.getEmail(), encryptedNewPassword);
        return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), null);
    }
}
