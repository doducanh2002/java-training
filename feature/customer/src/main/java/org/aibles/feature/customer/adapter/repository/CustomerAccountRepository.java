package org.aibles.feature.customer.adapter.repository;

import org.aibles.feature.customer.entity.CustomerAccount;

import java.util.List;

public interface CustomerAccountRepository {

    CustomerAccount save(CustomerAccount customerAccount);

    List<CustomerAccount> findByCustomerId(String customerId);
}
