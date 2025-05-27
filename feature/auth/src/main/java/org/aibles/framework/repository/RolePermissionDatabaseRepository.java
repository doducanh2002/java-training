package org.aibles.framework.repository;

import org.aibles.framework.datasource.RolePermissionDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDatabaseRepository extends JpaRepository<RolePermissionDatabaseEntity, String> {
}
