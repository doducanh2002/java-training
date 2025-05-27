package org.aibles.framework.repository;
import java.util.List;
import org.aibles.framework.datasource.PermissionDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionDatabaseEntityRepository extends
    JpaRepository<PermissionDatabaseEntity, String> {

  @Query("""
         select p from PermissionDatabaseEntity p left join RolePermissionDatabaseEntity rp
         on p.id = rp.permissionId where rp.roleId = :roleId
  """)
  List<PermissionDatabaseEntity> findAllByRoleId(String roleId);

}
