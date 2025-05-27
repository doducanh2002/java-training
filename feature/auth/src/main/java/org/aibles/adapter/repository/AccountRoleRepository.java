package org.aibles.adapter.repository;

import java.util.List;

public interface AccountRoleRepository {

  void save(String accountId, String roleId);

  void updateUserRoleByAccountId (String accountId, List<String> roles);
}
