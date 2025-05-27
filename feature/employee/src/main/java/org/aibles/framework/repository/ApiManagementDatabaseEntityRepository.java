package org.aibles.framework.repository;

import org.aibles.framework.datasource.ApiManagementDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiManagementDatabaseEntityRepository extends
    JpaRepository<ApiManagementDatabaseEntity, String> {

}
