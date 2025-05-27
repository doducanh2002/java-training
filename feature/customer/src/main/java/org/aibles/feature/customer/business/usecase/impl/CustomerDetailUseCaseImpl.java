package org.aibles.feature.customer.business.usecase.impl;

import java.util.Optional;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Customer;
import org.aibles.feature.customer.business.dto.response.CustomerDetailResponse;
import org.aibles.feature.customer.business.exception.CustomerNotFoundException;
import org.aibles.feature.customer.business.usecase.CustomerDetailUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerDetailUseCaseImpl implements CustomerDetailUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      CustomerDetailUseCaseImpl.class);

  private final CustomerRepository customerRepository;

  public CustomerDetailUseCaseImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public CustomerDetailResponse invoke(String credential) {
    log.info("(invoke)credential: {}", credential);
    Optional<Customer> cusOpt = customerRepository.findCustomerByAccountId(credential);
    return cusOpt.map(CustomerDetailResponse::from).orElseGet(() -> CustomerDetailResponse.from(
        customerRepository.findCustomerByEmail(credential).orElseThrow(() -> {
          log.error("(invoke)credential: {}", credential);
          return new CustomerNotFoundException(credential);
        })));
  }
}
