package org.aibles.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.entity.Role;

public interface RoleRepository {
  List<Role> getAllRoleByAccountId(String accountId);

  boolean existsById(String id);

  List<Role> getAllRoleByEmployeeId(String employeeId);

  List<Role> getAllRole();

  List<Role> getAllRoleByApiEndpointAndMethod(String apiEndpoint, String method);

  void saveAll(List<Role> roles);

  Role getRoleDetail(String roleId);

  Optional<Role> findByName(String roleName);
}
