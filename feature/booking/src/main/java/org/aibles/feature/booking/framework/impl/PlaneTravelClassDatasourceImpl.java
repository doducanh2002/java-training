package org.aibles.feature.booking.framework.impl;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PlaneTravelClassRepository;
import org.aibles.feature.booking.entity.PlaneTravelClass;
import org.aibles.feature.booking.framework.datasource.PlaneTravelClassDatabaseEntity;
import org.aibles.feature.booking.framework.repository.PlaneTravelClassDatabaseEntityRepository;
import org.aibles.feature.booking.util.PlaneTravelClassMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PlaneTravelClassDatasourceImpl implements PlaneTravelClassRepository {

  private final PlaneTravelClassDatabaseEntityRepository planeTravelClassDatabaseEntityRepository;

  @Override
  public PlaneTravelClass save(PlaneTravelClass planeTravelClass) {
    PlaneTravelClassDatabaseEntity entity = planeTravelClassDatabaseEntityRepository
        .save(PlaneTravelClassMapping.convertEntityToDatabase(planeTravelClass));
    return PlaneTravelClassMapping.convertDatabaseToEntity(entity);
  }

  @Override
  public Optional<PlaneTravelClass> findByNameAndPlane(String name, String planeId) {
    Optional<PlaneTravelClassDatabaseEntity> optionalPlaneTravelClassDatabaseEntity =
        planeTravelClassDatabaseEntityRepository.findByNameAndPlaneId(name, planeId);
    return optionalPlaneTravelClassDatabaseEntity.map(
        PlaneTravelClassMapping::convertDatabaseToEntity);
  }
}
