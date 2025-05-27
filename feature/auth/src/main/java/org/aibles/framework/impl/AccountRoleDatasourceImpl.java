package org.aibles.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.AccountRoleRepository;
import org.aibles.framework.datasource.AccountRoleDatabaseEntity;
import org.aibles.framework.repository.AccountRoleDatabaseEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AccountRoleDatasourceImpl implements AccountRoleRepository {

  private final AccountRoleDatabaseEntityRepository accountRoleDatabaseEntityRepository;

  @Override
  public void save(String accountId, String roleId) {
    accountRoleDatabaseEntityRepository.save(new AccountRoleDatabaseEntity(roleId, accountId));
  }

  @Override
  public void updateUserRoleByAccountId(String accountId, List<String> roles) {
    roles.stream().forEach(r -> accountRoleDatabaseEntityRepository.updateAccountRoleDatabaseEntityByAccountId(accountId, r));
  }
}
