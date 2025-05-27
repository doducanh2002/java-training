package org.aibles.feature.customer.business.usecase.impl;

import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_ACTIVE_ACCOUNT_KEY;
import static org.aibles.feature.customer.business.constant.CacheConstant.OTP_TTL_MINUTES;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.entity.Account;
import org.aibles.entity.Customer;
import org.aibles.feature.customer.adapter.repository.CustomerAccountRepository;
import org.aibles.feature.customer.adapter.repository.RedisRepository;
import org.aibles.feature.customer.business.usecase.SignUpUseCase;
import org.aibles.feature.customer.business.constant.ResponseCode;
import org.aibles.feature.customer.business.dto.request.SignupUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.BaseResponse;
import org.aibles.feature.customer.business.dto.response.SignupResponse;
import org.aibles.feature.customer.business.exception.EmailAlreadyExistedException;
import org.aibles.feature.customer.business.exception.PasswordConfirmNotMatchException;
import org.aibles.feature.customer.business.exception.UsernameAlreadyExistedException;
import org.aibles.feature.customer.entity.CustomerAccount;
import org.aibles.feature.customer.util.OTPGenerator;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpUseCaseImpl implements SignUpUseCase {

    private static final Logger log = LoggerFactory.getLogger(SignUpUseCaseImpl.class);
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final RedisRepository redisRepository;
    private final EmailHelper emailHelper;

    public SignUpUseCaseImpl(CustomerRepository customerRepository, AccountRepository accountRepository,
                             CustomerAccountRepository customerAccountRepository,
        RedisRepository redisRepository, EmailHelper emailHelper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.redisRepository = redisRepository;
        this.emailHelper = emailHelper;
    }

    @Override
    public BaseResponse<SignupResponse> invoke(SignupUseCaseRequest request) {
        if (customerRepository.existsCustomerByEmail(request.getEmail())) {
            log.error("(invoke)email: {}", request.getEmail());
            throw new EmailAlreadyExistedException(request.getEmail());
        }
        if (accountRepository.existsByUsername(request.getUsername())) {
            log.error("(invoke)username: {}", request.getUsername());
            throw new UsernameAlreadyExistedException(request.getUsername());
        }
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            log.error(
                "(invoke)password: {}, confirmPassword:{}  don't match",
                request.getPassword(),
                request.getConfirmPassword());
            throw new PasswordConfirmNotMatchException(request.getPassword(), request.getConfirmPassword());
        }

        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer = customerRepository.save(customer);

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(EncryptUtil.getPasswordEncoder().encode(request.getPassword()));
        account.setActivated(false);
        account.setLockPermanent(false);
        account = accountRepository.save(account);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setAccountId(account.getId());
        customerAccount.setCustomerId(customer.getId());
        customerAccountRepository.save(customerAccount);

        var otp = OTPGenerator.generateOtp();
        var redisKey = request.getEmail() + OTP_ACTIVE_ACCOUNT_KEY;
        redisRepository.save(redisKey, otp, OTP_TTL_MINUTES, TimeUnit.MINUTES);

        String subject = "Your OTP for account activation";
        var param = new HashMap<String, Object>();
        param.put("otp", otp);
        param.put("otp_life", String.valueOf(OTP_TTL_MINUTES));
        emailHelper.send(subject, request.getEmail(), "OTP-template", param);

        SignupResponse response = new SignupResponse(request.getEmail(), request.getUsername());
        return new BaseResponse<>(ResponseCode.SUCCESS.getValue(), System.currentTimeMillis(), response);
    }
}
