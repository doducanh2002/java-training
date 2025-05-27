package org.aibles.util;

import org.aibles.entity.ApiManagement;
import org.aibles.framework.datasource.ApiManagementDatabaseEntity;

public class ApiManagementMapping {

  public static ApiManagementDatabaseEntity mapDataSourceToEntity(ApiManagement apiManagement) {
    if (apiManagement == null) {
      return null;
    }
    ApiManagementDatabaseEntity entity = new ApiManagementDatabaseEntity();
    entity.setId(apiManagement.getId());
    entity.setMethod(apiManagement.getMethod());
    entity.setEndpoint(apiManagement.getEndpoint());
    entity.setDescription(apiManagement.getDescription());
    entity.setPermissionId(
        apiManagement.getPermission() != null ? apiManagement.getPermission().getId() : null);
    return entity;
  }
}
