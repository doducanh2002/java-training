package org.aibles.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.RolePermissionRepository;
import org.aibles.entity.RolePermission;
import org.aibles.framework.datasource.RolePermissionDatabaseEntity;
import org.aibles.framework.repository.RolePermissionDatabaseRepository;
import org.aibles.util.RolePermissionMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RolePermissionDataSourceImpl implements RolePermissionRepository {
    private final RolePermissionDatabaseRepository rolePermissionDatabaseRepository;

    @Override
    public void saveAll(List<RolePermission> rolePermissions) {
        List<RolePermissionDatabaseEntity> rolePermissionDatabaseEntities = rolePermissions.stream().map(RolePermissionMapping::toEntity).toList();
        rolePermissionDatabaseRepository.saveAll(rolePermissionDatabaseEntities);
    }

    @Override
    public void deleteAll() {
        rolePermissionDatabaseRepository.deleteAll();
    }
}
