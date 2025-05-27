package org.aibles.framework.impl;

import java.util.List;
import java.util.Optional;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.bussiness.dto.CustomerMilesAvailableProjectionUseCase;
import org.aibles.entity.Customer;
import org.aibles.framework.datasource.CustomerDatabaseEntity;
import org.aibles.framework.repository.CustomerDatabaseEntityRepository;
import org.aibles.framework.repository.CustomerMilesAvailableProjection;
import org.aibles.util.CustomerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CustomerDatasourceImpl implements CustomerRepository {

  private static final Logger log = LoggerFactory.getLogger(CustomerDatasourceImpl.class);

  private final CustomerDatabaseEntityRepository customerDatasourceRepository;

  public CustomerDatasourceImpl(CustomerDatabaseEntityRepository customerDatasourceRepository) {
    this.customerDatasourceRepository = customerDatasourceRepository;
  }


  @Override
  public Optional<Customer> findCustomerByEmail(String email) {
    log.info("(findCustomerByEmail)email: {}", email);
    Optional<CustomerDatabaseEntity> customerDatabaseEntityOptional =
        customerDatasourceRepository.findByEmail(email);
    if (customerDatabaseEntityOptional.isEmpty()) {
      return Optional.empty();
    }
    Customer customer = CustomerMapping.mapDataSourceToEntity(customerDatabaseEntityOptional.get());
    return Optional.of(customer);
  }

  @Override
  public boolean existsCustomerByEmail(String email) {
    log.info("(existsCustomerByEmail)email: {}", email);
    return customerDatasourceRepository.existsByEmail(email);
  }

  @Override
  public Optional<Customer> findCustomerByAccountId(String accountId) {
    log.info("(findCustomerByAccountId)accountId: {}", accountId);
    Optional<CustomerDatabaseEntity> customerDatabaseEntity = customerDatasourceRepository.findByAccountId(
        accountId);
    return customerDatabaseEntity.map(CustomerMapping::mapDataSourceToEntity);
  }

  @Override
  @Transactional
  public Customer save(Customer customer) {
      log.info("(save)customer: {}", customer);
      CustomerDatabaseEntity customerDatabase = CustomerMapping.mapEntityToDataSource(
          customer);
      return CustomerMapping.mapDataSourceToEntity(
          customerDatasourceRepository.save(customerDatabase));
  }

  @Override
  public CustomerMilesAvailableProjectionUseCase findIdAndMilesArchivedByAccountId(String accountId) {
    CustomerMilesAvailableProjection projection = customerDatasourceRepository.findMilesAvailableByAccountId(accountId);
    return CustomerMilesAvailableProjectionUseCase.builder()
        .id(projection.getId())
        .milesAvailable(projection.getMilesAvailable())
        .build();
  }

  @Override
  public Customer findByAccountId(String accountId) {
    log.info("(findCustomerByAccountId)accountId: {}", accountId);
    return CustomerMapping.mapDataSourceToEntity(customerDatasourceRepository.findById(accountId));
  }

  @Override
  public String getCustomerEmailByTicketId(String ticketId) {
    return customerDatasourceRepository.getCustomerEmailByTicketId(ticketId);
  }

  @Override
  public List<Customer> findAllCustomerByListId(List<String> customerIds) {
    List<CustomerDatabaseEntity> databaseEntityList = customerDatasourceRepository.findAllByListId(customerIds);
    return CustomerMapping.mapDataSourceToEntity(databaseEntityList);
  }
}