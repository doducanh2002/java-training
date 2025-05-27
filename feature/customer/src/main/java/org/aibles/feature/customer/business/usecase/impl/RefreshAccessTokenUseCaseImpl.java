package org.aibles.feature.customer.business.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.business.exception.ForbiddenException;
import org.aibles.entity.Account;
import org.aibles.entity.Customer;
import org.aibles.feature.customer.business.usecase.RefreshAccessTokenUseCase;
import org.aibles.feature.customer.business.dto.request.RefreshAccessTokenUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.RefreshAccessTokenUseCaseResponse;
import org.aibles.feature.customer.business.exception.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshAccessTokenUseCaseImpl implements RefreshAccessTokenUseCase {
    private static final Logger log = LoggerFactory.getLogger(RefreshAccessTokenUseCaseImpl.class);
    private final AuthenticationHelper authenticationHelper;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public RefreshAccessTokenUseCaseResponse invoke(
        RefreshAccessTokenUseCaseRequest request) {
        if(request.getRefreshToken() == null || request.getRefreshToken().isEmpty()) {
            throw new ForbiddenException();
        }
        if(authenticationHelper.isExpiredRefreshToken(request.getRefreshToken())) {
            throw new ForbiddenException();
        }
        var username = authenticationHelper.getSubjectFromRefreshToken(request.getRefreshToken());
        Account account = accountRepository.findAccountWithPermissionByUsername(username)
            .orElseThrow(() -> {
                log.error("(invoke)Invalid refresh token");
              return new ForbiddenException();
            });
        Customer customer = customerRepository.findCustomerByAccountId(account.getId())
            .orElseThrow(() -> {
                log.error("(invoke)Cant found customer with account id {}", account.getId());
                return new CustomerNotFoundException(account.getId());
            });
        RefreshAccessTokenUseCaseResponse response = new RefreshAccessTokenUseCaseResponse();
        response.setAccessToken(authenticationHelper.generateAccessToken(account.getUsername(), customer.getEmail()));
        response.setRefreshToken(authenticationHelper.generateRefreshToken(account.getUsername(), customer.getEmail()));
        response.setAccessTokenLifeTime(authenticationHelper.getAccessTokenLifeTime());
        response.setRefreshTokenLifeTime(authenticationHelper.getRefreshTokenLifeTime());
        return response;
    }
}
