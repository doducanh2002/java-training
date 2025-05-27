package org.aibles.adapter.repository;

import java.util.List;
import org.aibles.entity.Permission;

public interface PermissionRepository {
  List<Permission> getAllPermissionByRoleId(String roleId);

  void saveAll(List<Permission> permissions);

  void deleteAll();
}