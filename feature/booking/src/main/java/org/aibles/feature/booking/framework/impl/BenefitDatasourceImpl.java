package org.aibles.feature.booking.framework.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.BenefitRepository;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.framework.datasource.BenefitDatabaseEntity;
import org.aibles.feature.booking.framework.repository.BenefitDatabaseEntityRepository;
import org.aibles.feature.booking.util.BenefitMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BenefitDatasourceImpl implements BenefitRepository {

  private final BenefitDatabaseEntityRepository benefitDatabaseEntityRepository;

  @Override
  public List<Benefits> getBenefitsList() {
    return BenefitMapping.convertDatabaseToEntity(benefitDatabaseEntityRepository.findAll());
  }

  @Override
  public Optional<Benefits> findById(String id) {
    Optional<BenefitDatabaseEntity> optionalBenefitEntity = benefitDatabaseEntityRepository.findById(
        id);
    if (optionalBenefitEntity.isPresent()) {
      return Optional.of(BenefitMapping.convertDatabaseToEntity(optionalBenefitEntity.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Boolean existsById(String id) {
    return benefitDatabaseEntityRepository.existsById(id);
  }

  @Override
  public int countExistingIds(List<String> ids) {
    return benefitDatabaseEntityRepository.countExistingIds(ids);
  }

  @Override
  public List<Benefits> findAllByIds(List<String> ids) {
    return BenefitMapping.convertDatabaseToEntity(benefitDatabaseEntityRepository.findAllById(ids));
  }

  @Override
  public Benefits save(Benefits benefit) {
    return BenefitMapping.convertDatabaseToEntity(
        benefitDatabaseEntityRepository.save(BenefitMapping.convertEntityToDatabase(benefit)));
  }

  @Override
  public Optional<Benefits> findByName(String name) {
    Optional<BenefitDatabaseEntity> databaseEntity = benefitDatabaseEntityRepository
        .findByName(name);
    return databaseEntity.map(BenefitMapping::convertDatabaseToEntity);
  }

}
