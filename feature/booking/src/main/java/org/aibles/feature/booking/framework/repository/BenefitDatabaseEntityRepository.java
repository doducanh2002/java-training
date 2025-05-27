package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.BenefitDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitDatabaseEntityRepository extends
    JpaRepository<BenefitDatabaseEntity, String> {

  Optional<BenefitDatabaseEntity> findById(String id);

  @Query("SELECT COUNT(b.id) FROM BenefitDatabaseEntity b WHERE b.id IN :ids")
  int countExistingIds(List<String> ids);

  List<BenefitDatabaseEntity> findAllById(Iterable<String> ids);

  Optional<BenefitDatabaseEntity> findByName(String name);
}
