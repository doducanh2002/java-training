package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.FareBenefitRepository;
import org.aibles.feature.booking.entity.FareBenefit;
import org.aibles.feature.booking.framework.repository.FareBenefitDatabaseEntityRepository;
import org.aibles.feature.booking.util.FareBenefitMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FareBenefitDatasourceImpl implements FareBenefitRepository {

  private final FareBenefitDatabaseEntityRepository fareBenefitDatabaseEntityRepository;

  @Override
  public void saveAll(List<FareBenefit> fareBenefits) {
    fareBenefitDatabaseEntityRepository.saveAll(FareBenefitMapping.convertEntityToDatabase(fareBenefits));
  }

  @Override
  public boolean existsByFareIdAndBenefitId(String fareId, String benefitId) {
    return fareBenefitDatabaseEntityRepository.existsByFareIdAndBenefitId(fareId, benefitId);
  }
}
