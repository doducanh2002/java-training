package org.aibles.adapter.repository;

import java.util.Optional;
import org.aibles.entity.Account;

public interface AccountRepository {

  Optional<Account> findByAccountId(String id);

  void updatePasswordById(String id, String password);

  Account save(Account account);

  Optional<Account> findAccountsByEmail(String email);

  Optional<Account> findAccountWithPermissionByUsername(String username);

  boolean existsByUsername(String username);

  Optional<Account> findAccountsByCustomerId(String customerId);

  Optional<Account> findByUsername(String username);

  void updateLockPermanentById(String accountId, boolean isLockPermanent);

  void updatePasswordByEmail(String email, String password);

  String findPasswordByEmail(String email);

  int countAll();

  boolean existsById(String id);

  Optional<Account> findAccountByEmailEmployee(String email);

  void updatePasswordByEmailEmployee(String email, String password);

  Optional<Account> findAccountWithRolesByUsername(String username);

}
