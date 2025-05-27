package org.aibles.feature.booking.framework.repository;

import java.util.List;
import org.aibles.feature.booking.framework.datasource.PlaneBenefitDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneBenefitDatabaseEntityRepository extends
    JpaRepository<PlaneBenefitDatabaseEntity, String> {

  List<PlaneBenefitDatabaseEntity> findByPlaneId(String planeId);

}
