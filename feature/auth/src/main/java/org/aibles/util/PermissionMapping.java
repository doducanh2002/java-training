package org.aibles.util;

import org.aibles.entity.Permission;
import org.aibles.framework.datasource.PermissionDatabaseEntity;

public class PermissionMapping {
    public static Permission mapDataSourceToEntity(PermissionDatabaseEntity permissionDatabaseEntity) {
        if (permissionDatabaseEntity == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(permissionDatabaseEntity.getId());
        permission.setName(permissionDatabaseEntity.getName());
        permission.setDescription(permissionDatabaseEntity.getDescription());
        permission.setCreatedBy(permissionDatabaseEntity.getCreatedBy());
        permission.setCreatedAt(permissionDatabaseEntity.getCreatedAt());
        permission.setLastUpdatedBy(permissionDatabaseEntity.getLastUpdatedBy());
        permission.setLastUpdatedAt(permissionDatabaseEntity.getLastUpdatedAt());
        return permission;
    }

    public static PermissionDatabaseEntity mapDataSourceToEntity(Permission permission) {
        if (permission == null) {
            return null;
        }
        PermissionDatabaseEntity permissionDatabaseEntity = new PermissionDatabaseEntity();
        permissionDatabaseEntity.setId(permission.getId());
        permissionDatabaseEntity.setName(permission.getName());
        permissionDatabaseEntity.setDescription(permission.getDescription());
        permissionDatabaseEntity.setCreatedBy(permission.getCreatedBy());
        permissionDatabaseEntity.setCreatedAt(permission.getCreatedAt());
        permissionDatabaseEntity.setLastUpdatedBy(permission.getLastUpdatedBy());
        permissionDatabaseEntity.setLastUpdatedAt(permission.getLastUpdatedAt());
        return permissionDatabaseEntity;
    }
}

