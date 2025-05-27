package org.aibles.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "api_management")
@Entity
public class ApiManagementDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String method;
  private String endpoint;
  private String description;
  private String permissionId;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public ApiManagementDatabaseEntity() {
  }

  public ApiManagementDatabaseEntity(String id, String method, String endpoint, String description,
      String permissionId, String createdBy, Long createdAt, String lastUpdatedBy,
      Long lastUpdatedAt) {
    this.id = id;
    this.method = method;
    this.endpoint = endpoint;
    this.description = description;
    this.permissionId = permissionId;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.lastUpdatedBy = lastUpdatedBy;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(String permissionId) {
    this.permissionId = permissionId;
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
