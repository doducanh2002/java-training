package org.aibles.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "employee_account")
@Entity
public class EmployeeAccountDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String employeeId;
  private String accountId;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public EmployeeAccountDatabaseEntity() {
  }

  public EmployeeAccountDatabaseEntity(String id, String employeeId, String accountId,
      String createdBy, Long createdAt, String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.employeeId = employeeId;
    this.accountId = accountId;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.lastUpdatedBy = lastUpdatedBy;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public EmployeeAccountDatabaseEntity(String employeeId, String accountId) {
    this.employeeId = employeeId;
    this.accountId = accountId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public Long getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(Long lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }
}
