package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.BenefitDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.PlaneDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.PlaneBenefitProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneDatabaseEntityRepository extends JpaRepository<PlaneDatabaseEntity, String> {

  @Query(value = """
       SELECT p.id AS planeId, p.model AS planeModel, p.description AS planeDescription,
              b.id AS benefitId, b.name AS benefitName
       FROM PlaneDatabaseEntity p
       JOIN PlaneBenefitDatabaseEntity pb ON p.id = pb.planeId
       JOIN BenefitDatabaseEntity b ON pb.benefitId = b.id
      """)
  List<PlaneBenefitProjection> findPlanesWithBenefits();

  @Query(value = """
                SELECT b from BenefitDatabaseEntity b
                LEFT JOIN PlaneBenefitDatabaseEntity pb ON pb.benefitId = b.id
                LEFT JOIN PlaneDatabaseEntity p ON pb.planeId = p.id
                WHERE p.id = :id
                """)
  List<BenefitDatabaseEntity> findBenefitsById(String id);

  Optional<PlaneDatabaseEntity> findByModel(String model);

  @Query(value = """
       SELECT p.id AS planeId, p.model AS planeModel, p.description AS planeDescription,
              b.id AS benefitId, b.name AS benefitName
       FROM PlaneDatabaseEntity p
       JOIN PlaneBenefitDatabaseEntity pb ON p.id = pb.planeId
       JOIN BenefitDatabaseEntity b ON pb.benefitId = b.id
       WHERE p.id = :planeId
      """)
  List<PlaneBenefitProjection> findByPlaneId(String planeId);
}
