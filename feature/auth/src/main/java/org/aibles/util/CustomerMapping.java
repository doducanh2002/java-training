package org.aibles.util;


import java.util.ArrayList;
import java.util.List;
import org.aibles.entity.Customer;
import org.aibles.framework.datasource.CustomerDatabaseEntity;

public class CustomerMapping {

    public static Customer mapDataSourceToEntity(CustomerDatabaseEntity customerDatabaseEntity) {
        if(customerDatabaseEntity == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDatabaseEntity.getId());
        customer.setFirstName(customerDatabaseEntity.getFirstName());
        customer.setMiddleName(customerDatabaseEntity.getMiddleName());
        customer.setLastName(customerDatabaseEntity.getLastName());
        customer.setEmail(customerDatabaseEntity.getEmail());
        customer.setPhone(customerDatabaseEntity.getPhone());
        customer.setDateOfBirth(customerDatabaseEntity.getDateOfBirth());
        customer.setGender(customerDatabaseEntity.getGender());
        customer.setAddress(customerDatabaseEntity.getAddress());
        customer.setAvatar(customerDatabaseEntity.getAvatar());
        customer.setMilesAvailable(customerDatabaseEntity.getMilesAvailable());
        customer.setCreatedBy(customerDatabaseEntity.getCreatedBy());
        customer.setCreatedAt(customerDatabaseEntity.getCreatedAt());
        customer.setLastUpdatedBy(customerDatabaseEntity.getLastUpdatedBy());
        customer.setLastUpdatedAt(customerDatabaseEntity.getLastUpdatedAt());
        return customer;
    }

    public static CustomerDatabaseEntity mapEntityToDataSource(Customer customer) {
        CustomerDatabaseEntity customerDatabase = new CustomerDatabaseEntity();
        customerDatabase.setId(customer.getId());
        customerDatabase.setFirstName(customer.getFirstName());
        customerDatabase.setMiddleName(customer.getMiddleName());
        customerDatabase.setLastName(customer.getLastName());
        customerDatabase.setEmail(customer.getEmail());
        customerDatabase.setPhone(customer.getPhone());
        customerDatabase.setDateOfBirth(customer.getDateOfBirth());
        customerDatabase.setGender(customer.getGender());
        customerDatabase.setAddress(customer.getAddress());
        customerDatabase.setAvatar(customer.getAvatar());
        customerDatabase.setMilesAvailable(customer.getMilesAvailable());
        customerDatabase.setCreatedBy(customer.getCreatedBy());
        customerDatabase.setCreatedAt(customer.getCreatedAt());
        customerDatabase.setLastUpdatedBy(customer.getLastUpdatedBy());
        customerDatabase.setLastUpdatedAt(customer.getLastUpdatedAt());
        return customerDatabase;
    }

    public static List<Customer> mapDataSourceToEntity(List<CustomerDatabaseEntity> databaseEntityList) {
        if(databaseEntityList == null || databaseEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        return databaseEntityList.stream().map(CustomerMapping::mapDataSourceToEntity).toList();
    }

}
