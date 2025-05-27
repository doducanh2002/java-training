package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.adapter.repository.ReservedMealRepository;
import org.aibles.feature.booking.entity.ReservedMeal;
import org.aibles.feature.booking.framework.repository.ReservedMealDatabaseEntityRepository;
import org.aibles.feature.booking.util.ReservedMealMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReservedMealDatasourceImpl implements ReservedMealRepository {

  private final ReservedMealDatabaseEntityRepository reservedMealDatabaseEntityRepository;

  @Override
  @Transactional
  public void saveAll(List<ReservedMeal> reservedMeals) {
    reservedMealDatabaseEntityRepository.saveAll(
        ReservedMealMapping.convertEntityToDatabase(reservedMeals));
  }
}
