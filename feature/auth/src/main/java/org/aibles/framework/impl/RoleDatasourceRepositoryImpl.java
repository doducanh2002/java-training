package org.aibles.framework.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.bussiness.exception.RoleNotFoundException;
import org.aibles.entity.Permission;
import org.aibles.entity.Role;
import org.aibles.framework.datasource.RoleDatabaseEntity;
import org.aibles.framework.datasource.RolePermissionProjection;
import org.aibles.framework.repository.RoleDatabaseEntityRepository;
import org.aibles.util.RoleMapping;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleDatasourceRepositoryImpl implements RoleRepository {

  private final RoleDatabaseEntityRepository roleDatabaseEntityRepository;

  @Override
  public List<Role> getAllRoleByAccountId(String accountId) {
    List<RoleDatabaseEntity> roleDatabaseEntities = roleDatabaseEntityRepository.findAllByAccountId(
        accountId);
    return roleDatabaseEntities.stream().filter(Objects::nonNull).map(RoleMapping::mapToEntity)
        .toList();
  }

  @Override
  public boolean existsById(String id) {
    return roleDatabaseEntityRepository.existsById(id);
  }

  @Override
  public List<Role> getAllRoleByEmployeeId(String employeeId) {
    List<RoleDatabaseEntity> roleDatabaseEntities = roleDatabaseEntityRepository.getAllRoleByEmployeeId(
        employeeId);
    return roleDatabaseEntities.stream().filter(Objects::nonNull).map(RoleMapping::mapToEntity)
        .toList();
  }

  @Override
  public List<Role> getAllRole() {
    List<RoleDatabaseEntity> roleDatabaseEntities = roleDatabaseEntityRepository.findAll();
    return roleDatabaseEntities.stream().map(RoleMapping::mapToEntity).toList();
  }

  @Override
  public List<Role> getAllRoleByApiEndpointAndMethod(String apiEndpoint, String method) {
    List<RoleDatabaseEntity> roleDatabaseEntities = roleDatabaseEntityRepository.findAllRoleByApiEndpointAndMethod(apiEndpoint, method);
    return roleDatabaseEntities.stream().filter(Objects::nonNull).map(RoleMapping::mapToEntity)
            .toList();
  }

  @Override
  public void saveAll(List<Role> roles) {
    List<RoleDatabaseEntity> roleDatabaseEntities = roles.stream().map(RoleMapping::mapToDatasource).toList();
    roleDatabaseEntityRepository.saveAll(roleDatabaseEntities);
  }

  @Override
  public Role getRoleDetail(String roleId) {
    List<RolePermissionProjection> listRolePermission = roleDatabaseEntityRepository.getRoleDetailByRoleId(roleId);
    Role role = new Role();
    if (listRolePermission.isEmpty()) {
      throw new RoleNotFoundException(roleId);
    }
    role.setId(listRolePermission.get(0).getRoleId());
    role.setName(listRolePermission.get(0).getRoleName());
    List<Permission> permissionList = new ArrayList<>();
    listRolePermission.forEach(rp -> {
      Permission p = new Permission();
      p.setId(rp.getPermissionId());
      p.setName(rp.getPermissionName());
      permissionList.add(p);
    });
    role.setPermissions(permissionList);
    return role;
  }

  @Override
  public Optional<Role> findByName(String roleName) {
    Optional<RoleDatabaseEntity> optionalRoleDatabaseEntity =
        roleDatabaseEntityRepository.findByName(roleName);
    if (optionalRoleDatabaseEntity.isEmpty()) {
      return Optional.empty();
    }
    return optionalRoleDatabaseEntity.map(RoleMapping::mapToEntity);
  }
}
