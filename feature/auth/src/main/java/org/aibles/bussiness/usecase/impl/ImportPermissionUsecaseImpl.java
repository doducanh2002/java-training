package org.aibles.bussiness.usecase.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.adapter.repository.ApiManagementRepository;
import org.aibles.adapter.repository.PermissionRepository;
import org.aibles.adapter.repository.RolePermissionRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.bussiness.dto.ImportPermissionDTO;
import org.aibles.bussiness.usecase.ImportPermissionUseCase;
import org.aibles.entity.ApiManagement;
import org.aibles.entity.Permission;
import org.aibles.entity.Role;
import org.aibles.entity.RolePermission;

public class ImportPermissionUsecaseImpl implements ImportPermissionUseCase {

  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;
  private final ApiManagementRepository apiManagementRepository;
  private final RolePermissionRepository rolePermissionRepository;
  private final ExcelHelper excelHelper;

  public ImportPermissionUsecaseImpl(RoleRepository roleRepository,
      PermissionRepository permissionRepository, ApiManagementRepository apiManagementRepository,
      RolePermissionRepository rolePermissionRepository,
      ExcelHelper excelHelper) {
    this.roleRepository = roleRepository;
    this.permissionRepository = permissionRepository;
    this.apiManagementRepository = apiManagementRepository;
    this.rolePermissionRepository = rolePermissionRepository;
    this.excelHelper = excelHelper;
  }

  @Override
  public void invoke(InputStream inputStream) {
    List<ImportPermissionDTO> invoiceRecords = excelHelper.importPermission(inputStream);

    List<Permission> permissions = new ArrayList<>();
    List<Role> roles = new ArrayList<>();
    List<ApiManagement> apiManagements = new ArrayList<>();
    List<RolePermission> rolePermissions = new ArrayList<>();
    Role role;
    ApiManagement apiManagement;
    RolePermission rolePermission;
    Permission permission;

    Set<String> setRoles = invoiceRecords.stream()
        .map(ImportPermissionDTO::getRole)
        .filter(element -> element != null && !element.isEmpty())
        .collect(Collectors.toSet());

    for (String element : setRoles) {
      Optional<Role> optionalRole = roleRepository.findByName(element);
      role = new Role();
      if (optionalRole.isEmpty()) {
        role.setId(UUID.randomUUID().toString());
        role.setName(element);
      } else {
        role = optionalRole.get();
      }
      roles.add(role);
    }

    deleteAllPermissions();
    for (ImportPermissionDTO importPermissionDTO : invoiceRecords) {
      role = roles.stream()
          .filter(element -> element.getName().equalsIgnoreCase(importPermissionDTO.getRole()))
          .findFirst().get();
      permission = new Permission();
      apiManagement = new ApiManagement();
      rolePermission = new RolePermission();

      permission.setName(importPermissionDTO.getPermission());
      permission.setId(UUID.randomUUID().toString());
      permissions.add(permission);

      rolePermission.setPermissionId(permission.getId());
      rolePermission.setRoleId(role.getId());
      rolePermissions.add(rolePermission);

      apiManagement.setEndpoint(importPermissionDTO.getEndpoint());
      apiManagement.setMethod(importPermissionDTO.getMethod());
      apiManagement.setDescription(importPermissionDTO.getDescription());
      apiManagement.setPermission(permission);
      apiManagements.add(apiManagement);
    }

    roleRepository.saveAll(roles);
    permissionRepository.saveAll(permissions);
    apiManagementRepository.saveAll(apiManagements);
    rolePermissionRepository.saveAll(rolePermissions);
  }

  private void deleteAllPermissions() {
    rolePermissionRepository.deleteAll();
    apiManagementRepository.deleteAll();
    permissionRepository.deleteAll();
  }
}
