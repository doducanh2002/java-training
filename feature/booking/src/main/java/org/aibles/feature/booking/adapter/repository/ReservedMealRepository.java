package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.ReservedMeal;

public interface ReservedMealRepository {

  void saveAll(List<ReservedMeal> reservedMeals);
}
