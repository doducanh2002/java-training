package org.aibles.framework.repository;

import jakarta.transaction.Transactional;
import org.aibles.framework.datasource.AccountRoleDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleDatabaseEntityRepository extends JpaRepository<AccountRoleDatabaseEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE AccountRoleDatabaseEntity ar SET ar.roleId = :roleId WHERE ar.accountId = :accountId")
    void updateAccountRoleDatabaseEntityByAccountId(String accountId, String roleId);
}
