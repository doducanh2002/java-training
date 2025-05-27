package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Map;
import org.aibles.feature.booking.entity.Meal;
import org.springframework.data.jpa.repository.Query;

public interface MealRepository {

  List<Meal> getMealByFlightId(String flightId);

  void saveAll(List<Meal> mealList);

  List<Meal> getMealByIdIn(List<String> mealIds);

  List<Meal> findByFlightIdAndId(Map<String, String> mealMap);
}
