package org.aibles.feature.booking.framework.impl;

import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;
import org.aibles.feature.booking.adapter.repository.MealRepository;
import org.aibles.feature.booking.entity.Meal;
import org.aibles.feature.booking.framework.datasource.MealDatabaseEntity;
import org.aibles.feature.booking.framework.repository.MealDatabaseEntityRepository;
import org.aibles.feature.booking.util.MealMapping;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MealDatasourceImpl implements MealRepository {

  private final MealDatabaseEntityRepository mealDatabaseEntityRepository;

  public MealDatasourceImpl(MealDatabaseEntityRepository mealDatabaseEntityRepository) {
    this.mealDatabaseEntityRepository = mealDatabaseEntityRepository;
  }

  @Override
  public List<Meal> getMealByFlightId(String flightId) {
    return mealDatabaseEntityRepository.findAllByFlightId(flightId)
        .stream().map(MealMapping::convertDatasourceToEntity)
        .toList();
  }

  @Transactional
  @Override
  public void saveAll(List<Meal> mealList) {
    mealDatabaseEntityRepository.saveAll(MealMapping.convertEntityToDatasource(mealList));
  }

  @Override
  public List<Meal> getMealByIdIn(List<String> mealIds) {
    return mealDatabaseEntityRepository.findAllByIdIn(mealIds).stream()
        .map(MealMapping::convertDatasourceToEntity)
        .toList();
  }

  @Override
  public List<Meal> findByFlightIdAndId(Map<String, String> mealMap) {
    Specification<MealDatabaseEntity> specification = (root, query, builder) -> {
      List<Predicate> predicates = mealMap.entrySet().stream().map(entry -> builder.and(
              builder.equal(root.get("flightId"), entry.getKey()),
              builder.equal(root.get("id"), entry.getValue())
          ))
          .toList();
      return builder.or(predicates.toArray(new Predicate[0]));
    };
    return mealDatabaseEntityRepository.findAll(specification)
        .stream().map(MealMapping::convertDatasourceToEntity).toList();
  }

}
