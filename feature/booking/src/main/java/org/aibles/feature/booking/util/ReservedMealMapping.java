package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Meal;
import org.aibles.feature.booking.entity.ReservedMeal;
import org.aibles.feature.booking.entity.ReservedTrip;
import org.aibles.feature.booking.framework.datasource.ReservedMealEntity;

public class ReservedMealMapping {

  public static ReservedMeal convertDatabaseToEntity(ReservedMealEntity databaseEntity) {
    ReservedMeal reservedMeal = new ReservedMeal();
    reservedMeal.setId(databaseEntity.getId());
    ReservedTrip reservedTrip = new ReservedTrip();
    reservedTrip.setId(databaseEntity.getReservedTripId());
    reservedMeal.setReservedTrip(reservedTrip);
    Meal meal = new Meal();
    meal.setId(databaseEntity.getMealId());
    reservedMeal.setMeal(meal);
    reservedMeal.setPrice(databaseEntity.getPrice());
    return reservedMeal;
  }

  public static List<ReservedMeal> convertDatabaseToEntity(
      List<ReservedMealEntity> reservedSeatDatabaseEntities) {
    List<ReservedMeal> reservedSeats = new ArrayList<>();
    for (ReservedMealEntity databaseEntity : reservedSeatDatabaseEntities) {
      ReservedMeal reservedSeat = ReservedMealMapping.convertDatabaseToEntity(databaseEntity);
      reservedSeats.add(reservedSeat);
    }
    return reservedSeats;
  }

  public static ReservedMealEntity convertEntityToDatabase(ReservedMeal reservedMeal) {
    ReservedMealEntity databaseEntity = new ReservedMealEntity();
    databaseEntity.setId(reservedMeal.getId());
    databaseEntity.setReservedTripId(reservedMeal.getReservedTrip().getId());
    databaseEntity.setMealId(reservedMeal.getMeal().getId());
    databaseEntity.setPrice(reservedMeal.getPrice());
    return databaseEntity;
  }

  public static List<ReservedMealEntity> convertEntityToDatabase(
      List<ReservedMeal> reservedMeals) {
    List<ReservedMealEntity> databaseEntities = new ArrayList<>();
    for (ReservedMeal reservedMeal : reservedMeals) {
      ReservedMealEntity databaseEntity = ReservedMealMapping.convertEntityToDatabase(
          reservedMeal);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }
}
