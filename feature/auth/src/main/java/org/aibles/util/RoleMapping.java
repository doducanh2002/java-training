package org.aibles.util;

import org.aibles.entity.Role;
import org.aibles.framework.datasource.RoleDatabaseEntity;

public class RoleMapping {

    public static Role mapToEntity(RoleDatabaseEntity roleDatabaseEntity) {
        if (roleDatabaseEntity == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDatabaseEntity.getId());
        role.setName(roleDatabaseEntity.getName());
        role.setCreatedBy(roleDatabaseEntity.getCreatedBy());
        role.setCreatedAt(roleDatabaseEntity.getCreatedAt());
        role.setLastUpdatedBy(roleDatabaseEntity.getLastUpdatedBy());
        role.setLastUpdatedAt(roleDatabaseEntity.getLastUpdatedAt());
        return role;
    }

    public static RoleDatabaseEntity mapToDatasource(Role role) {
        if (role == null) {
            return null;
        }
        RoleDatabaseEntity roleDatabaseEntity = new RoleDatabaseEntity();
        roleDatabaseEntity.setId(role.getId());
        roleDatabaseEntity.setName(role.getName());
        roleDatabaseEntity.setCreatedBy(role.getCreatedBy());
        roleDatabaseEntity.setCreatedAt(role.getCreatedAt());
        roleDatabaseEntity.setLastUpdatedBy(role.getLastUpdatedBy());
        roleDatabaseEntity.setLastUpdatedAt(role.getLastUpdatedAt());
        return roleDatabaseEntity;
    }
}