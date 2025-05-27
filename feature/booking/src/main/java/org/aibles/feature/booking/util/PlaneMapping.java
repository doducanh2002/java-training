package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.framework.datasource.PlaneDatabaseEntity;

public class PlaneMapping {

  public static Plane convertDatabaseToEntity(PlaneDatabaseEntity databaseEntity) {
    Plane plane = new Plane();
    plane.setId(databaseEntity.getId());
    plane.setDescription(databaseEntity.getDescription());
    plane.setModel(databaseEntity.getModel());
    return plane;
  }

  public static List<Plane> convertDatabaseToEntity(
      List<PlaneDatabaseEntity> planeDatabaseEntities) {
    List<Plane> planes = new ArrayList<>();
    for (PlaneDatabaseEntity databaseEntity : planeDatabaseEntities) {
      Plane plane = PlaneMapping.convertDatabaseToEntity(databaseEntity);
      planes.add(plane);
    }
    return planes;
  }

  public static Plane convertIdToPlane(String planeId) {
    Plane plane = new Plane();
    plane.setId(planeId);
    return plane;
  }

  public static PlaneDatabaseEntity convertEntityToDatabase(Plane plane) {
    PlaneDatabaseEntity databaseEntity = new PlaneDatabaseEntity();
    databaseEntity.setId(plane.getId());
    databaseEntity.setDescription(plane.getDescription());
    databaseEntity.setModel(plane.getModel());
    return databaseEntity;
  }
}
