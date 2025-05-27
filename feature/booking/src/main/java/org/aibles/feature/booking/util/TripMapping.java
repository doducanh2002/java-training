package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.entity.Trip;
import org.aibles.feature.booking.framework.datasource.AirportDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.TripDatabaseEntity;

public class TripMapping {

  public static Trip convertDatabaseToEntity(TripDatabaseEntity databaseEntity) {
    Trip trip = new Trip();
    trip.setId(databaseEntity.getId());
    trip.setStartDate(databaseEntity.getStartDate());
    trip.setEndDate(databaseEntity.getEndDate());
    trip.setDeparture(databaseEntity.getDeparture());
    trip.setArrival(databaseEntity.getArrival());
    return trip;
  }

  public static TripDatabaseEntity convertEntityToDatabase(Trip trip) {
    TripDatabaseEntity databaseEntity = new TripDatabaseEntity();
    databaseEntity.setId(trip.getId());
    databaseEntity.setStartDate(trip.getStartDate());
    databaseEntity.setEndDate(trip.getEndDate());
    databaseEntity.setDeparture(trip.getDeparture());
    databaseEntity.setArrival(trip.getArrival());
    return databaseEntity;
  }

  public static List<Trip> convertDatabaseToEntity(
      List<TripDatabaseEntity> tripDatabaseEntities) {
    List<Trip> trips = new ArrayList<>();
    for (TripDatabaseEntity databaseEntity : tripDatabaseEntities) {
      Trip trip = TripMapping.convertDatabaseToEntity(databaseEntity);
      trips.add(trip);
    }
    return trips;
  }

  public static List<TripDatabaseEntity> convertEntityToDatabase(
      List<Trip> trips) {
    List<TripDatabaseEntity> databaseEntities = new ArrayList<>();
    for (Trip databaseEntity : trips) {
      TripDatabaseEntity database = TripMapping.convertEntityToDatabase(databaseEntity);
      databaseEntities.add(database);
    }
    return databaseEntities;
  }
}
