package org.aibles.feature.booking.util;

import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.framework.datasource.FareDatabaseEntity;

public class FareMapping {

  public static FareDatabaseEntity convertEntityToDatabase(Fare fare) {
    FareDatabaseEntity databaseEntity = new FareDatabaseEntity();
    databaseEntity.setId(fare.getId());
    databaseEntity.setName(fare.getName());
    databaseEntity.setPrice(fare.getPrice());
    databaseEntity.setTravelClassId(fare.getTravelClass().getId());
    return databaseEntity;
  }

  public static Fare convertDatabaseToEntity(FareDatabaseEntity databaseEntity) {
    Fare fare = new Fare();
    fare.setId(databaseEntity.getId());
    fare.setName(databaseEntity.getName());
    fare.setPrice(databaseEntity.getPrice());
    TravelClass travelClass = new TravelClass();
    travelClass.setId(databaseEntity.getId());
    fare.setTravelClass(travelClass);
    return fare;
  }
}
