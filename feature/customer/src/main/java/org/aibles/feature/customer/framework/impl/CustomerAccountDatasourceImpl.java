package org.aibles.feature.customer.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.customer.adapter.repository.CustomerAccountRepository;
import org.aibles.feature.customer.entity.CustomerAccount;
import org.aibles.feature.customer.framework.datasource.CustomerAccountDatabaseEntity;
import org.aibles.feature.customer.framework.repository.CustomerAccountDatabaseEntityRepository;
import org.aibles.feature.customer.util.CustomerAccountMapping;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CustomerAccountDatasourceImpl implements CustomerAccountRepository {

    private final CustomerAccountDatabaseEntityRepository customerAccountDatabaseEntityRepository;

    @Override
    public CustomerAccount save(CustomerAccount customerAccount) {
        log.info("(save)customerAccount: {}", customerAccount);
        CustomerAccountDatabaseEntity customerAccountDatabase = CustomerAccountMapping.mapEntityToDataSource(customerAccount);
        return CustomerAccountMapping.mapDataSourceToEntity(customerAccountDatabaseEntityRepository.save(customerAccountDatabase));
    }

    @Override
    public List<CustomerAccount> findByCustomerId(String customerId) {
        List<CustomerAccountDatabaseEntity> customerAccountDatabaseEntities = customerAccountDatabaseEntityRepository.findByCustomerId(customerId);
        return customerAccountDatabaseEntities.stream().map(CustomerAccountMapping::mapDatabaseEntityToCustomerAccount).collect(Collectors.toList());
    }
}
