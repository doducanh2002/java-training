package org.aibles.feature.customer.util;

import org.aibles.feature.customer.entity.CustomerAccount;
import org.aibles.feature.customer.framework.datasource.CustomerAccountDatabaseEntity;

public class CustomerAccountMapping {

  public static CustomerAccount mapDataSourceToEntity(
      CustomerAccountDatabaseEntity customerAccountDatabase) {
    CustomerAccount customerAccount = new CustomerAccount();
    customerAccount.setId(customerAccountDatabase.getId());
    customerAccount.setCustomerId(customerAccountDatabase.getCustomerId());
    customerAccount.setAccountId(customerAccountDatabase.getAccountId());
    return customerAccount;
  }

    public static CustomerAccountDatabaseEntity mapEntityToDataSource(
            CustomerAccount customerAccount) {
        CustomerAccountDatabaseEntity customerAccountDatabase = new CustomerAccountDatabaseEntity();
        customerAccountDatabase.setId(customerAccount.getId());
        customerAccountDatabase.setCustomerId(customerAccount.getCustomerId());
        customerAccountDatabase.setAccountId(customerAccount.getAccountId());
        return customerAccountDatabase;
    }

    public static CustomerAccount mapDatabaseEntityToCustomerAccount(CustomerAccountDatabaseEntity customerAccountDatabaseEntity) {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setId(customerAccountDatabaseEntity.getId());
        customerAccount.setAccountId(customerAccountDatabaseEntity.getAccountId());
        customerAccount.setCustomerId(customerAccountDatabaseEntity.getCustomerId());
        return customerAccount;
    }
}
