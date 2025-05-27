package org.aibles.feature.customer.entity;

public class CustomerAccount {

    private String id;
    private String customerId;
    private String accountId;

    public CustomerAccount() {
    }

    public CustomerAccount(String id, String customerId, String accountId) {
        this.id = id;
        this.customerId = customerId;
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

