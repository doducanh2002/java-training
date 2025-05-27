package org.aibles.feature.booking.framework.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PlaneBenefitRepository;
import org.aibles.feature.booking.entity.PlaneBenefit;
import org.aibles.feature.booking.framework.datasource.PlaneBenefitDatabaseEntity;
import org.aibles.feature.booking.framework.repository.PlaneBenefitDatabaseEntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PlaneBenefitDatasourceImpl implements PlaneBenefitRepository {

  private final PlaneBenefitDatabaseEntityRepository planeBenefitDatabaseEntityRepository;

  @Override
  @Transactional
  public void save(String benefitId, String planeId) {
    PlaneBenefitDatabaseEntity databaseEntity = new PlaneBenefitDatabaseEntity();
    databaseEntity.setPlaneId(planeId);
    databaseEntity.setBenefitId(benefitId);
    planeBenefitDatabaseEntityRepository.save(databaseEntity);
  }

  @Override
  public void saveAll(List<PlaneBenefit> planeBenefits) {
    List<PlaneBenefitDatabaseEntity> entities = planeBenefits.stream().map(dto -> {
      PlaneBenefitDatabaseEntity entity = new PlaneBenefitDatabaseEntity();
      entity.setId(dto.getId());
      entity.setPlaneId(dto.getPlane().getId());
      entity.setBenefitId(dto.getBenefit().getId());
      return entity;
    }).collect(Collectors.toList());
    planeBenefitDatabaseEntityRepository.saveAll(entities);
  }
}
