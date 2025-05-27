package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.PlaneBenefit;

public interface PlaneBenefitRepository {

  void save(String benefitId, String planeId);
  void saveAll(List<PlaneBenefit> planeBenefits);
}
