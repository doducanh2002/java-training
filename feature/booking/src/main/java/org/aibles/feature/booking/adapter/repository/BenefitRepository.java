package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.Benefits;

public interface BenefitRepository {

  List<Benefits> getBenefitsList();

  Optional<Benefits> findById(String id);

  Boolean existsById(String id);
  int countExistingIds(List<String> ids);

  List<Benefits> findAllByIds(List<String> ids);

  Benefits save(Benefits benefit);

  Optional<Benefits> findByName(String name);
}
