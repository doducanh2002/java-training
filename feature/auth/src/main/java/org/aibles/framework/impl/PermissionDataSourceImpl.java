package org.aibles.framework.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.aibles.adapter.repository.PermissionRepository;
import org.aibles.entity.Permission;
import org.aibles.framework.datasource.PermissionDatabaseEntity;
import org.aibles.framework.repository.PermissionDatabaseEntityRepository;
import org.aibles.util.PermissionMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PermissionDataSourceImpl implements PermissionRepository {

  private final PermissionDatabaseEntityRepository permissionDatabaseEntityRepository;

  @Override
  public List<Permission> getAllPermissionByRoleId(String roleId) {
    List<PermissionDatabaseEntity> permissionDatabaseEntityList = permissionDatabaseEntityRepository.findAllByRoleId(
        roleId);
    return permissionDatabaseEntityList.stream().filter(Objects::nonNull)
        .map(PermissionMapping::mapDataSourceToEntity)
        .toList();
  }

  @Override
  public void saveAll(List<Permission> permissions) {
    List<PermissionDatabaseEntity> permissionDatabaseEntityList = permissions.stream()
            .map(PermissionMapping::mapDataSourceToEntity).toList();
    permissionDatabaseEntityRepository.saveAll(permissionDatabaseEntityList);
  }

  @Override
  public void deleteAll() {
    permissionDatabaseEntityRepository.deleteAll();
  }
}
