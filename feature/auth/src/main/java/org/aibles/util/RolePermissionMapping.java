package org.aibles.util;

import org.aibles.entity.RolePermission;
import org.aibles.framework.datasource.RolePermissionDatabaseEntity;

public class RolePermissionMapping {
    public static RolePermissionDatabaseEntity toEntity(RolePermission domain) {
        if (domain == null) {
            return null;
        }

        RolePermissionDatabaseEntity entity = new RolePermissionDatabaseEntity();
        entity.setId(domain.getId());
        entity.setRoleId(domain.getRoleId());
        entity.setPermissionId(domain.getPermissionId());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setLastUpdatedBy(domain.getLastUpdatedBy());
        entity.setLastUpdatedAt(domain.getLastUpdatedAt());
        return entity;
    }
}
