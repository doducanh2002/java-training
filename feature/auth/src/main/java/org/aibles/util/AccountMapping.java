package org.aibles.util;

import org.aibles.entity.Account;
import org.aibles.framework.datasource.AccountDatabaseEntity;

public class AccountMapping {

  public static Account mapDataSourceToEntity(AccountDatabaseEntity accountDatabaseEntity) {
    if (accountDatabaseEntity == null) {
      return null;
    }
    Account account = new Account();
    account.setId(accountDatabaseEntity.getId());
    account.setUsername(accountDatabaseEntity.getUsername());
    account.setPassword(accountDatabaseEntity.getPassword());
    account.setActivated(accountDatabaseEntity.getActivated());
    account.setLockPermanent(accountDatabaseEntity.getLockPermanent());
    account.setCustomerId(accountDatabaseEntity.getCustomerId());
    account.setCreatedAt(accountDatabaseEntity.getCreatedAt());
    account.setCreatedBy(accountDatabaseEntity.getCreatedBy());
    account.setLastUpdatedAt(accountDatabaseEntity.getLastUpdatedAt());
    account.setLastUpdatedBy(accountDatabaseEntity.getLastUpdatedBy());
    return account;
  }

  public static AccountDatabaseEntity mapAccountToDatabaseEntity(Account account) {
    AccountDatabaseEntity accountDatabaseEntity = new AccountDatabaseEntity();
    accountDatabaseEntity.setId(account.getId());
    accountDatabaseEntity.setUsername(account.getUsername());
    accountDatabaseEntity.setPassword(account.getPassword());
    accountDatabaseEntity.setActivated(account.getActivated());
    accountDatabaseEntity.setLockPermanent(account.getLockPermanent());
    accountDatabaseEntity.setCreatedBy(account.getCreatedBy());
    accountDatabaseEntity.setCreatedAt(account.getCreatedAt());
    accountDatabaseEntity.setLastUpdatedBy(account.getLastUpdatedBy());
    accountDatabaseEntity.setLastUpdatedAt(account.getLastUpdatedAt());
    return accountDatabaseEntity;
  }
}
