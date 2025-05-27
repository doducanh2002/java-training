package org.aibles.framework.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.PermissionRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.entity.Account;
import org.aibles.entity.Permission;
import org.aibles.entity.Role;
import org.aibles.framework.datasource.AccountDatabaseEntity;
import org.aibles.framework.repository.AccountDatabaseEntityRepository;
import org.aibles.util.AccountMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AccountDatasourceImpl implements AccountRepository {

  private final AccountDatabaseEntityRepository accountDatabaseEntityRepository;
  private final PermissionRepository permissionRepository;
  private final RoleRepository roleRepository;

  @Override
  public Optional<Account> findByAccountId(String id) {
    Optional<AccountDatabaseEntity> accountDatabaseEntity = accountDatabaseEntityRepository.findById(
        id);
    if (accountDatabaseEntity.isEmpty()) {
      return Optional.empty();
    }
    Account account = AccountMapping.mapDataSourceToEntity(accountDatabaseEntity.get());
    return Optional.of(account);
  }

  @Override
  public Account save(Account account) {
    AccountDatabaseEntity accountDatabaseEntity = AccountMapping.mapAccountToDatabaseEntity(
        account);
    accountDatabaseEntity = accountDatabaseEntityRepository.save(accountDatabaseEntity);
    return AccountMapping.mapDataSourceToEntity(accountDatabaseEntity);
  }

  @Override
  @Transactional
  public void updatePasswordById(String id, String password) {
    accountDatabaseEntityRepository.updatePasswordById(id, password);
  }

  @Override
  public boolean existsByUsername(String username) {
    return accountDatabaseEntityRepository.existsByUsername(username);
  }

  @Override
  @Transactional
  public Optional<Account> findAccountsByCustomerId(String customerId) {
    Optional<AccountDatabaseEntity> accountDatabaseEntityOptional =
        accountDatabaseEntityRepository.findByCustomerId(customerId);
    return accountDatabaseEntityOptional.map(AccountMapping::mapDataSourceToEntity);
  }

  @Override
  public Optional<Account> findByUsername(String username) {
    Optional<AccountDatabaseEntity> accountOptional = accountDatabaseEntityRepository.findByUsername(
        username);
    if (accountOptional.isEmpty()) {
      return Optional.empty();
    }
    return accountOptional.map(AccountMapping::mapDataSourceToEntity);
  }

  @Override
  public void updateLockPermanentById(String accountId, boolean isLockPermanent) {
    accountDatabaseEntityRepository.updateLockPermanentById(accountId, isLockPermanent);
  }

  @Override
  @Transactional
  public void updatePasswordByEmail(String email, String password) {
    accountDatabaseEntityRepository.updatePasswordByEmail(email, password);
  }

  @Override
  @Transactional
  public Optional<Account> findAccountsByEmail(String email) {
    Optional<AccountDatabaseEntity> accountDatabaseEntityOptional =
        accountDatabaseEntityRepository.findAccountsByEmail(email);
    return accountDatabaseEntityOptional.map(AccountMapping::mapDataSourceToEntity);
  }

  @Override
  public Optional<Account> findAccountWithPermissionByUsername(String username) {
    Optional<AccountDatabaseEntity> accountDatabaseOpt = accountDatabaseEntityRepository.findByUsername(
        username);
    if (accountDatabaseOpt.isEmpty()) {
      return Optional.empty();
    }
    AccountDatabaseEntity accountDatabaseEntity = accountDatabaseOpt.get();
    Account account = AccountMapping.mapDataSourceToEntity(accountDatabaseEntity);
    List<Role> roleList = roleRepository.getAllRoleByAccountId(account.getId());
    roleList.forEach(r -> {
      String roleId = r.getId();
      List<Permission> permissionList = permissionRepository.getAllPermissionByRoleId(roleId);
      r.setPermissions(permissionList);
    });
    account.setRoles(roleList);
    return Optional.of(account);
  }

  @Override
  public String findPasswordByEmail(String email) {
    return accountDatabaseEntityRepository.findPasswordByEmail(email);
  }

  @Override
  public int countAll() {
    return accountDatabaseEntityRepository.countAll();
  }

  @Override
  public boolean existsById(String id) {
    return accountDatabaseEntityRepository.existsById(id);
  }

  @Override
  public Optional<Account> findAccountByEmailEmployee(String email) {
    Optional<AccountDatabaseEntity> accountDatabaseEntityOptional =
        accountDatabaseEntityRepository.findAccountByEmailEmployee(email);
    return accountDatabaseEntityOptional.map(AccountMapping::mapDataSourceToEntity);
  }

  @Override
  @Transactional
  public void updatePasswordByEmailEmployee(String email, String password) {
    String id = accountDatabaseEntityRepository.findIdByEmailEmployee(email);
    accountDatabaseEntityRepository.updatePasswordById(id, password);
  }

  @Override
  public Optional<Account> findAccountWithRolesByUsername(String username) {
    Optional<AccountDatabaseEntity> accountDatabaseOpt = accountDatabaseEntityRepository.findByUsername(username);
    if (accountDatabaseOpt.isEmpty()) {
      return Optional.empty();
    }
    AccountDatabaseEntity accountDatabaseEntity = accountDatabaseOpt.get();
    Account account = AccountMapping.mapDataSourceToEntity(accountDatabaseEntity);
    List<Role> roleList = roleRepository.getAllRoleByAccountId(account.getId());
    account.setRoles(roleList);
    return Optional.of(account);
  }
}
