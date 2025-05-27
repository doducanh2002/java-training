package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.FareBenefitDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FareBenefitDatabaseEntityRepository extends JpaRepository<FareBenefitDatabaseEntity, String> {

  boolean existsByFareIdAndBenefitId(String fareId, String benefitId);
}
