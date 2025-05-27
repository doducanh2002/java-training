package org.aibles.framework.repository;

import java.util.Optional;
import org.aibles.framework.datasource.AccountDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDatabaseEntityRepository extends
    JpaRepository<AccountDatabaseEntity, String> {

  Optional<AccountDatabaseEntity> findByCustomerId(String customerId);

  @Modifying
  @Query("update AccountDatabaseEntity a set a.password = :newPassword where a.id = :id")
  void updatePasswordById(String id, String newPassword);
  Optional<AccountDatabaseEntity> findById(String id);
  @Query("SELECT a FROM AccountDatabaseEntity a JOIN CustomerAccountDatabaseEntity ca ON a.id = ca.accountId JOIN CustomerDatabaseEntity c ON c.id = ca.customerId WHERE c.email = :email")
  Optional<AccountDatabaseEntity> findAccountsByEmail(String email);

  Optional<AccountDatabaseEntity> findByUsername(String username);

  boolean existsByUsername(String username);

  @Query("SELECT a FROM AccountDatabaseEntity a JOIN CustomerAccountDatabaseEntity ca ON a.id = ca.accountId WHERE ca.customerId = :customerId")
  Optional<AccountDatabaseEntity> findAccountsByCustomerId(String customerId);

  @Modifying
  @Query("update AccountDatabaseEntity a set a.isLockPermanent = :isLockPermanent where a.id = :id")
  void updateLockPermanentById(String id, boolean isLockPermanent);

  @Modifying
  @Query("update AccountDatabaseEntity a set a.password = :newPassword " +
      "where a.id = (select ca.accountId from CustomerAccountDatabaseEntity ca " +
      "join CustomerDatabaseEntity c on ca.customerId = c.id " +
      "where c.email = :email)")
  void updatePasswordByEmail(String email, String newPassword);

  @Query("""
        SELECT a.password FROM AccountDatabaseEntity a 
        JOIN CustomerAccountDatabaseEntity ca ON a.id = ca.accountId 
        JOIN CustomerDatabaseEntity c ON c.id = ca.customerId 
        WHERE c.email = :email
        """)
  String findPasswordByEmail(String email);

  @Query("SELECT COUNT(a) FROM AccountDatabaseEntity a")
  int countAll();

  @Query("""
    SELECT a FROM AccountDatabaseEntity a JOIN EmployeeAccountDatabaseEntity ea ON a.id = ea.accountId JOIN EmployeeDatabaseEntity e ON e.id = ea.employeeId WHERE e.email = :email
  """)
  Optional<AccountDatabaseEntity> findAccountByEmailEmployee(String email);

  @Query("select ea.accountId from EmployeeAccountDatabaseEntity ea " +
      "join EmployeeDatabaseEntity e on ea.employeeId = e.id " +
      "where e.email = :email")
  String findIdByEmailEmployee(String email);
}
