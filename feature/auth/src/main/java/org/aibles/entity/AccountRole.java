package org.aibles.entity;


public class AccountRole {

    private String id;
    private String roleId;
    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public AccountRole() {
    }

    public AccountRole(String id, String roleId, String accountId) {
        this.id = id;
        this.roleId = roleId;
        this.accountId = accountId;
    }
}
