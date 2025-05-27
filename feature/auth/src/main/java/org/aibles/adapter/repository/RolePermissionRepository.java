package org.aibles.adapter.repository;

import org.aibles.entity.RolePermission;

import java.util.List;

public interface RolePermissionRepository {

    void saveAll(List<RolePermission> rolePermissions);

    void deleteAll();
}
