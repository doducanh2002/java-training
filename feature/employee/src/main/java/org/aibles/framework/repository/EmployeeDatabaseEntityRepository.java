package org.aibles.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.framework.datasource.EmployeeDatabaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeDatabaseEntityRepository extends
    JpaRepository<EmployeeDatabaseEntity, String> {

  boolean existsByEmail(String email);

  @Query("""
    SELECT e FROM EmployeeDatabaseEntity e
    JOIN EmployeeAccountDatabaseEntity ea ON ea.employeeId = e.id
    WHERE ea.accountId = :accountId
""")
  Optional<EmployeeDatabaseEntity> findByAccountId(String accountId);

  @Modifying
  @Transactional
  @Query("UPDATE EmployeeDatabaseEntity e SET e.isFirstLogin = :isFirstLogin WHERE e.email = :email")
  void updateIsFirstLoginByEmail(String email, boolean isFirstLogin);

  Page<EmployeeDatabaseEntity> findAllByIsDeletedFalse(Pageable pageable);

  @Modifying
  @Transactional
  @Query("UPDATE EmployeeDatabaseEntity e SET e.isDeleted = :isDeleted WHERE e.id IN :ids")
  void updateIsDeletedById(List<String> ids, boolean isDeleted);
}
