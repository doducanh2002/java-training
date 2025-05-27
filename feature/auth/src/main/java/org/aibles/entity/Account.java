package org.aibles.entity;

import java.util.List;

public class Account {
    private String id;
    private String username;
    private String password;
    private Boolean isActivated;
    private Boolean isLockPermanent;
    private String customerId;
    private String createdBy;
    private String createdAt;
    private String lastUpdatedBy;
    private String lastUpdatedAt;
    private List<Role> roles;

    public Account() {
    }

    public Account(String id, String username, String password, Boolean isActivated, Boolean isLockPermanent,
                   String customerId, String createdBy, String createdAt, String lastUpdatedBy, String lastUpdatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActivated = isActivated;
        this.isLockPermanent = isLockPermanent;
        this.customerId = customerId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Account(String username, String password, Boolean isActivated) {
        this.username = username;
        this.password = password;
        this.isActivated = isActivated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getLockPermanent() {
        return isLockPermanent;
    }

    public void setLockPermanent(Boolean lockPermanent) {
        isLockPermanent = lockPermanent;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}


