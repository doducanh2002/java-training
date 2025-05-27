package org.aibles.adapter.repository;


import java.util.List;
import java.util.Optional;
import org.aibles.bussiness.dto.CustomerMilesAvailableProjectionUseCase;
import org.aibles.entity.Customer;

public interface CustomerRepository {

  Optional<Customer> findCustomerByEmail(String email);

  boolean existsCustomerByEmail(String email);

  Optional<Customer> findCustomerByAccountId(String accountId);

  Customer save(Customer customer);

  CustomerMilesAvailableProjectionUseCase findIdAndMilesArchivedByAccountId(String accountId);

  Customer findByAccountId(String accountId);

  String getCustomerEmailByTicketId(String ticketId);

  List<Customer> findAllCustomerByListId(List<String> customerIds);
 }
 
