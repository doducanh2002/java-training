package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.framework.datasource.TravelClassDatabaseEntity;

public class TravelClassMapping {
  public static TravelClass convertDatabaseToEntity(TravelClassDatabaseEntity databaseEntity) {
    TravelClass travelClass = new TravelClass();
    travelClass.setId(databaseEntity.getId());
    travelClass.setName(databaseEntity.getName());
    travelClass.setDescription(databaseEntity.getDescription());
    return travelClass;
  }

  public static List<TravelClass> convertDatabaseToEntity(
      List<TravelClassDatabaseEntity> travelClassDatabaseEntities) {
    List<TravelClass> travelClasses = new ArrayList<>();
    for (TravelClassDatabaseEntity databaseEntity : travelClassDatabaseEntities) {
      TravelClass travelClass = TravelClassMapping.convertDatabaseToEntity(databaseEntity);
      travelClasses.add(travelClass);
    }
    return travelClasses;
  }

  public static TravelClassDatabaseEntity convertEntityToDatabase(TravelClass travelClass) {
    TravelClassDatabaseEntity databaseEntity = new TravelClassDatabaseEntity();
    databaseEntity.setId(travelClass.getId());
    databaseEntity.setName(travelClass.getName());
    databaseEntity.setDescription(travelClass.getDescription());
    return databaseEntity;
  }
}
