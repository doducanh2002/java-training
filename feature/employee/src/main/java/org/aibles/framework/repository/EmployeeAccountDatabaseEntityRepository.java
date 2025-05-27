package org.aibles.framework.repository;

import org.aibles.framework.datasource.EmployeeAccountDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccountDatabaseEntityRepository extends
    JpaRepository<EmployeeAccountDatabaseEntity, String> {

    Optional<EmployeeAccountDatabaseEntity> findByEmployeeId(String employeeId);

    Optional<EmployeeAccountDatabaseEntity> findByAccountId(String accountId);
}
