package org.aibles.framework.repository;

import java.util.List;

import java.util.Optional;
import org.aibles.framework.datasource.RoleDatabaseEntity;
import org.aibles.framework.datasource.RolePermissionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleDatabaseEntityRepository extends JpaRepository<RoleDatabaseEntity, String> {

    @Query("""
                  select r from RoleDatabaseEntity r left join AccountRoleDatabaseEntity ar
                  on r.id = ar.roleId where ar.accountId = :accountId
            """)
    List<RoleDatabaseEntity> findAllByAccountId(String accountId);

    @Query("""
                  SELECT r FROM RoleDatabaseEntity r 
                  JOIN AccountRoleDatabaseEntity ar ON r.id = ar.roleId
                  JOIN AccountDatabaseEntity a ON ar.accountId = a.id
                  JOIN EmployeeAccountDatabaseEntity ea ON a.id = ea.accountId
                  WHERE ea.employeeId = :employeeId
            """)
    List<RoleDatabaseEntity> getAllRoleByEmployeeId(String employeeId);

    @Query(value = """
                    SELECT DISTINCT r
                    FROM RoleDatabaseEntity r
                    JOIN RolePermissionDatabaseEntity rp ON r.id = rp.roleId
                    JOIN PermissionDatabaseEntity p ON rp.permissionId = p.id
                    JOIN ApiManagementDatabaseEntity am ON p.id = am.permissionId
                    WHERE am.endpoint = :endpoint
                    AND am.method = :method
            """)
    List<RoleDatabaseEntity> findAllRoleByApiEndpointAndMethod(@Param("endpoint") String apiEndpoint, String method);


    @Query("""
                SELECT new org.aibles.framework.datasource.RolePermissionProjection(
                    rp.roleId, r.name, rp.permissionId, p.name
                )
                FROM RolePermissionDatabaseEntity rp
                JOIN PermissionDatabaseEntity p ON rp.permissionId = p.id
                JOIN RoleDatabaseEntity r ON rp.roleId = r.id
                WHERE rp.roleId = :roleId
            """)
    List<RolePermissionProjection> getRoleDetailByRoleId(String roleId);

    Optional<RoleDatabaseEntity> findByName(String roleName);
}
