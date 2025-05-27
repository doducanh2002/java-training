package org.aibles.framework.repository;

import org.aibles.framework.datasource.LoginDataDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataDatabaseEntityRepository extends
    JpaRepository<LoginDataDatabaseEntity, Long> {
}
