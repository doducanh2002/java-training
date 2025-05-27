package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.PlaneTravelClass;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.framework.datasource.PlaneTravelClassDatabaseEntity;

public class PlaneTravelClassMapping {

  public static List<PlaneTravelClass> convertDatabaseToEntity(List<PlaneTravelClassDatabaseEntity> planeTravelClassDatabaseEntities) {
    List<PlaneTravelClass>  planeTravelClassList = new ArrayList<>();
    for (PlaneTravelClassDatabaseEntity planeTravelClassDatabaseEntity : planeTravelClassDatabaseEntities) {
      PlaneTravelClass travelClass = PlaneTravelClassMapping.convertDatabaseToEntity(planeTravelClassDatabaseEntity);
      planeTravelClassList.add(travelClass);
    }
    return planeTravelClassList;
  }

  public static PlaneTravelClass convertDatabaseToEntity(PlaneTravelClassDatabaseEntity planeTravelClassDatabaseEntity) {
    PlaneTravelClass planeTravelClass = new PlaneTravelClass();
    planeTravelClass.setId(planeTravelClassDatabaseEntity.getId());
    planeTravelClass.setCapacity(planeTravelClassDatabaseEntity.getCapacity());
    Plane plane = new Plane();
    plane.setId(planeTravelClassDatabaseEntity.getPlaneId());
    planeTravelClass.setPlane(plane);
    TravelClass travelClass = new TravelClass();
    travelClass.setId(planeTravelClassDatabaseEntity.getTravelClassId());
    planeTravelClass.setTravelClass(travelClass);
    return planeTravelClass;
  }

  public static PlaneTravelClassDatabaseEntity convertEntityToDatabase(PlaneTravelClass planeTravelClass) {
    PlaneTravelClassDatabaseEntity databaseEntity = new PlaneTravelClassDatabaseEntity();
    databaseEntity.setId(planeTravelClass.getId());
    databaseEntity.setCapacity(planeTravelClass.getCapacity());
    databaseEntity.setPlaneId(planeTravelClass.getPlane().getId());
    databaseEntity.setTravelClassId(planeTravelClass.getTravelClass().getId());
    return databaseEntity;
  }

  public static List<PlaneTravelClassDatabaseEntity> convertEntityToDatabase(List<PlaneTravelClass> planeTravelClasses) {
    List<PlaneTravelClassDatabaseEntity>  planeTravelClassList = new ArrayList<>();
    for (PlaneTravelClass planeTravelClassDatabaseEntity : planeTravelClasses) {
      PlaneTravelClassDatabaseEntity travelClass = PlaneTravelClassMapping.convertEntityToDatabase(planeTravelClassDatabaseEntity);
      planeTravelClassList.add(travelClass);
    }
    return planeTravelClassList;
  }
}
