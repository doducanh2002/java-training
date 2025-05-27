package org.aibles.bussiness.dto;

public class ImportPermissionDTO {
  private String id;
  private String method;
  private String endpoint;
  private String description;
  private String permission;
  private String role;

  public ImportPermissionDTO() {}

  public ImportPermissionDTO(
      String id,
      String method,
      String endpoint,
      String description,
      String permission,
      String role) {
    this.id = id;
    this.method = method;
    this.endpoint = endpoint;
    this.description = description;
    this.permission = permission;
    this.role = role;
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

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
