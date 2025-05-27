package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.PlaneBenefit;
import org.aibles.feature.booking.framework.datasource.PlaneBenefitDatabaseEntity;

public class PlaneBenefitMapping {

  public static PlaneBenefit convertDatabaseToEntity(PlaneBenefitDatabaseEntity databaseEntity) {
    PlaneBenefit planeBenefit = new PlaneBenefit();
    planeBenefit.setId(databaseEntity.getId());
    Plane plane = new Plane();
    plane.setId(databaseEntity.getPlaneId());
    planeBenefit.setPlane(plane);
    Benefits benefit = new Benefits();
    benefit.setId(databaseEntity.getBenefitId());
    planeBenefit.setBenefit(benefit);
    return planeBenefit;
  }

  public static List<PlaneBenefit> convertDatabaseToEntity(
      List<PlaneBenefitDatabaseEntity> databaseEntities) {
    List<PlaneBenefit> planeBenefitList = new ArrayList<>();
    for (PlaneBenefitDatabaseEntity databaseEntity : databaseEntities) {
      PlaneBenefit planeBenefit = PlaneBenefitMapping.convertDatabaseToEntity(databaseEntity);
      planeBenefitList.add(planeBenefit);
    }
    return planeBenefitList;
  }
}
