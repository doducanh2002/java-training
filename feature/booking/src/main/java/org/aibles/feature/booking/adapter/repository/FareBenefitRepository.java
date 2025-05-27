package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.FareBenefit;

public interface FareBenefitRepository {

  void saveAll(List<FareBenefit> fareBenefits);

  boolean existsByFareIdAndBenefitId(String fareId, String benefitId);
}
