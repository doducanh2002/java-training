package org.aibles.feature.booking.framework.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.framework.datasource.PlaneDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.PlaneBenefitProjection;
import org.aibles.feature.booking.framework.repository.PlaneDatabaseEntityRepository;
import org.aibles.feature.booking.util.PlaneMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PlaneDatasourceImpl implements PlaneRepository {

  private final PlaneDatabaseEntityRepository planeDatabaseEntityRepository;

  @Override
  public List<Plane> findPlanesWithBenefits() {
    List<PlaneBenefitProjection> projections = planeDatabaseEntityRepository.findPlanesWithBenefits();
    return convertProjectionToEntity(projections);
  }

  @Override
  @Transactional
  public Plane create(Plane plane) {
    var planeDatabaseEntity = PlaneMapping.convertEntityToDatabase(plane);
    return PlaneMapping.convertDatabaseToEntity(planeDatabaseEntityRepository.save(planeDatabaseEntity));
  }

  @Override
  public Optional<Plane> findByModel(String model) {
    Optional<PlaneDatabaseEntity> planeDatabaseEntity = planeDatabaseEntityRepository.findByModel(model);
    return planeDatabaseEntity.map(PlaneMapping::convertDatabaseToEntity);
  }

  @Override
  public Optional<Plane> findById(String id) {
    List<PlaneBenefitProjection> projections = planeDatabaseEntityRepository.findByPlaneId(id);
    return convertProjectionsToEntity(projections);
  }

  private List<Plane> convertProjectionToEntity(List<PlaneBenefitProjection> projections) {
    Map<String, List<Benefits>> planeBenefitsMap = new HashMap<>();
    Map<String, Plane> planeMap = new HashMap<>();
    for (PlaneBenefitProjection projection : projections) {
      String planeId = projection.getPlaneId();
      planeMap.computeIfAbsent(planeId, id -> {
        Plane newPlane = new Plane();
        newPlane.setId(id);
        newPlane.setModel(projection.getPlaneModel());
        newPlane.setDescription(projection.getPlaneDescription());
        return newPlane;
      });
      planeBenefitsMap.putIfAbsent(planeId, new ArrayList<>());
      Benefits benefits = new Benefits();
      benefits.setId(projection.getBenefitId());
      benefits.setName(projection.getBenefitName());
      planeBenefitsMap.get(planeId).add(benefits);
    }
    List<Plane> planes = new ArrayList<>();
    planeMap.keySet().stream().filter(
        planeBenefitsMap::containsKey
    ).forEach(planeId -> {
      Plane plane = planeMap.get(planeId);
      List<Benefits> benefits = planeBenefitsMap.get(planeId);
      plane.setBenefits(benefits);
      planes.add(plane);
    });
    return planes;
  }

  private Optional<Plane> convertProjectionsToEntity(List<PlaneBenefitProjection> projections) {
    if (projections == null || projections.isEmpty()) {
      return Optional.empty();
    }

    PlaneBenefitProjection firstProjection = projections.get(0);

    Plane plane = new Plane();
    plane.setId(firstProjection.getPlaneId());
    plane.setModel(firstProjection.getPlaneModel());
    plane.setDescription(firstProjection.getPlaneDescription());

    List<Benefits> benefitsList = new ArrayList<>();
    for (PlaneBenefitProjection projection : projections) {
      Benefits benefits = new Benefits();
      benefits.setId(projection.getBenefitId());
      benefits.setName(projection.getBenefitName());
      benefitsList.add(benefits);
    }

    plane.setBenefits(benefitsList);

    return Optional.of(plane);
  }

  @Override
  @Transactional
  public Plane update(Plane plane) {
    var planeDatabaseEntity = PlaneMapping.convertEntityToDatabase(plane);
    return PlaneMapping.convertDatabaseToEntity(planeDatabaseEntityRepository.save(planeDatabaseEntity));
  }

  @Override
  public Plane findByPlaneId(String id) {
    Optional<PlaneDatabaseEntity> planeDatabaseEntity = planeDatabaseEntityRepository.findById(id);
    return planeDatabaseEntity.map(PlaneMapping::convertDatabaseToEntity).orElse(null);
  }
}
