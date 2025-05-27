package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.SpecialAssistanceRepository;
import org.aibles.feature.booking.entity.SpecialAssistance;
import org.aibles.feature.booking.framework.datasource.SpecialAssistanceDatabaseEntity;
import org.aibles.feature.booking.framework.repository.SpecialAssistanceDatabaseEntityRepository;
import org.aibles.feature.booking.util.SpecialAssistanceMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SpecialAssistanceDatasourceImpl implements SpecialAssistanceRepository {
  private final SpecialAssistanceDatabaseEntityRepository specialAssistanceDatabaseEntityRepository;

  @Override
  public List<SpecialAssistance> findAll() {
    List<SpecialAssistanceDatabaseEntity> data =
        specialAssistanceDatabaseEntityRepository.findAll();
    return SpecialAssistanceMapping.convertToEntity(data);
  }
}
