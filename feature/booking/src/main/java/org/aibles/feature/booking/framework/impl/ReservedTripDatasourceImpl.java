package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.adapter.repository.ReservedTripRepository;
import org.aibles.feature.booking.entity.ReservedTrip;
import org.aibles.feature.booking.framework.repository.ReservedTripDatabaseEntityRepository;
import org.aibles.feature.booking.util.ReservedTripMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservedTripDatasourceImpl implements ReservedTripRepository {

  private final ReservedTripDatabaseEntityRepository reservedTripDatabaseEntityRepository;

  @Override
  public List<ReservedTrip> saveAll(List<ReservedTrip> reservedTrips) {
    return ReservedTripMapping.convertDatabaseToEntity(reservedTripDatabaseEntityRepository.saveAll(
        ReservedTripMapping.convertEntityToDatabase(reservedTrips)));
  }
}
