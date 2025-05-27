package org.aibles.feature.booking.util;

import java.util.List;
import org.aibles.feature.booking.entity.Meal;
import org.aibles.feature.booking.framework.datasource.MealDatabaseEntity;

public class MealMapping {

  public static Meal convertDatasourceToEntity(MealDatabaseEntity databaseEntity) {
    Meal meal = new Meal();
    meal.setId(databaseEntity.getId());
    meal.setDish(databaseEntity.getDish());
    meal.setMealTime(databaseEntity.getMealTime());
    meal.setPrice(databaseEntity.getPrice());
    return meal;
  }

  public static MealDatabaseEntity convertEntityToDatasource(Meal meal) {
    MealDatabaseEntity databaseEntity = new MealDatabaseEntity();
    databaseEntity.setId(meal.getId());
    databaseEntity.setDish(meal.getDish());
    databaseEntity.setMealTime(meal.getMealTime());
    databaseEntity.setPrice(meal.getPrice());
    databaseEntity.setFlightId(meal.getFlight().getId());
    return databaseEntity;
  }

  public static List<MealDatabaseEntity> convertEntityToDatasource(List<Meal> mealList) {
    List<MealDatabaseEntity> databaseEntityList = new java.util.ArrayList<>();
    for (Meal meal : mealList) {
      MealDatabaseEntity databaseEntity = MealMapping.convertEntityToDatasource(meal);
      databaseEntityList.add(databaseEntity);
    }
    return databaseEntityList;
  }
}
