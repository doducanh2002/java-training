package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.framework.datasource.AirportDatabaseEntity;

public class AirportMapping {
  public static Airport convertDatabaseToEntity(AirportDatabaseEntity databaseEntity) {
    Airport airport = new Airport();
    airport.setId(databaseEntity.getId());
    airport.setCountry(databaseEntity.getCountry());
    airport.setRegion(databaseEntity.getRegion());
    airport.setName(databaseEntity.getName());
    airport.setCode(databaseEntity.getCode());
    return airport;
  }

  public static List<Airport> convertDatabaseToEntity(
      List<AirportDatabaseEntity> airportDatabaseEntityList) {
    List<Airport> airports = new ArrayList<>();
    for (AirportDatabaseEntity databaseEntity : airportDatabaseEntityList) {
      Airport airport = AirportMapping.convertDatabaseToEntity(databaseEntity);
      airports.add(airport);
    }
    return airports;
  }

  public static AirportDatabaseEntity convertEntityToDatabase(Airport airport) {
    AirportDatabaseEntity databaseEntity = new AirportDatabaseEntity();
    databaseEntity.setId(airport.getId());
    databaseEntity.setCountry(airport.getCountry());
    databaseEntity.setRegion(airport.getRegion());
    databaseEntity.setName(airport.getName());
    databaseEntity.setCode(airport.getCode());
    return databaseEntity;
  }
}
