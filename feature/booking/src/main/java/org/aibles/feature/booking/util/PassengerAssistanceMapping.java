package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.PassengerAssistance;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.entity.SpecialAssistance;
import org.aibles.feature.booking.framework.datasource.PassengerAssistanceDatabaseEntity;

public class PassengerAssistanceMapping {

  public static PassengerAssistance convertDatabaseToEntity(
      PassengerAssistanceDatabaseEntity databaseEntity) {
    PassengerAssistance passengerAssistance = new PassengerAssistance();
    passengerAssistance.setId(databaseEntity.getId());
    PassengerInfo passengerInfo = new PassengerInfo();
    passengerInfo.setId(databaseEntity.getId());
    passengerAssistance.setPassengerInfo(passengerInfo);
    SpecialAssistance specialAssistance = new SpecialAssistance();
    specialAssistance.setId(databaseEntity.getId());
    passengerAssistance.setSpecialAssistance(specialAssistance);
    return passengerAssistance;
  }

  public static List<PassengerAssistance> convertDatabaseToEntity(
      List<PassengerAssistanceDatabaseEntity> databaseEntities) {
    List<PassengerAssistance> passengerAssistances = new ArrayList<>();
    for (PassengerAssistanceDatabaseEntity databaseEntity : databaseEntities) {
      PassengerAssistance passengerAssistance = PassengerAssistanceMapping.convertDatabaseToEntity(databaseEntity);
      passengerAssistances.add(passengerAssistance);
    }
    return passengerAssistances;
  }

  public static PassengerAssistanceDatabaseEntity convertEntityToDatabase(PassengerAssistance passengerAssistance) {
    PassengerAssistanceDatabaseEntity databaseEntity = new PassengerAssistanceDatabaseEntity();
    databaseEntity.setId(passengerAssistance.getId());
    databaseEntity.setPassengerId(passengerAssistance.getPassengerInfo().getId());
    databaseEntity.setAssistanceId(passengerAssistance.getSpecialAssistance().getId());
    return databaseEntity;
  }

  public static List<PassengerAssistanceDatabaseEntity> convertEntityToDatabase(
      List<PassengerAssistance> passengerAssistances) {
    List<PassengerAssistanceDatabaseEntity> databaseEntities = new ArrayList<>();
    for (PassengerAssistance passengerAssistance : passengerAssistances) {
      PassengerAssistanceDatabaseEntity databaseEntity = PassengerAssistanceMapping.convertEntityToDatabase(
          passengerAssistance);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }
}
