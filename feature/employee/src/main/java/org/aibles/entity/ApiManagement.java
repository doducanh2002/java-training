package org.aibles.entity;

public class ApiManagement {

  private String id;
  private String method;
  private String endpoint;
  private String description;
  private Permission permission;

  public ApiManagement() {
  }

  public ApiManagement(String id, String method, String endpoint, String description,
      Permission permission) {
    this.id = id;
    this.method = method;
    this.endpoint = endpoint;
    this.description = description;
    this.permission = permission;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }
}
