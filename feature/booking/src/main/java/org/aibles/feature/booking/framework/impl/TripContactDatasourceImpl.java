package org.aibles.feature.booking.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.TripContactRepository;
import org.aibles.feature.booking.entity.TripContact;
import org.aibles.feature.booking.framework.repository.TripContactDatabaseEntityRepository;
import org.aibles.feature.booking.util.TripContactMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TripContactDatasourceImpl implements TripContactRepository {

  private final TripContactDatabaseEntityRepository tripContactDatabaseEntityRepository;

  @Override
  @Transactional
  public void save(TripContact tripContact) {
    tripContactDatabaseEntityRepository.save(TripContactMapping.convertEntityToDatabase(tripContact));
  }
}
