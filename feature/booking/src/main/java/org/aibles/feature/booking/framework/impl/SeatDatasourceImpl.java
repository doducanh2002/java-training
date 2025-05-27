package org.aibles.feature.booking.framework.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.SeatRepository;
import org.aibles.feature.booking.entity.Seat;
import org.aibles.feature.booking.framework.datasource.SeatDatabaseEntity;
import org.aibles.feature.booking.framework.repository.SeatDatabaseEntityRepository;
import org.aibles.feature.booking.util.SeatMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SeatDatasourceImpl implements SeatRepository {

  private final SeatDatabaseEntityRepository seatDatabaseEntityRepository;

  @Override
  @Transactional
  public void save(Seat seat) {
    SeatDatabaseEntity databaseEntity = SeatMapping.convertEntityToDatabase(seat);
    seatDatabaseEntityRepository.save(databaseEntity);
  }

  @Override
  public void saveAll(List<Seat> seats) {
    seatDatabaseEntityRepository.saveAll(SeatMapping.convertEntityToDatabase(seats));
  }

  @Override
  public Optional<Seat> findByPlaneTravelClassAndSeatRowAndSeatColumn(
      String planeTravelClassId, int seatRow, String seatColumn) {
    Optional<SeatDatabaseEntity> optionalSeatDatabaseEntity =
        seatDatabaseEntityRepository.findByPlaneTravelClassIdAndSeatRowAndSeatColumn(
            planeTravelClassId, seatRow, seatColumn);
    return optionalSeatDatabaseEntity.map(SeatMapping::convertDatabaseToEntity);
  }

  @Override
  public List<Seat> findByFareIdAndFlightId(String fareId, String flightId) {
    List<SeatDatabaseEntity> seatDatabaseEntities = seatDatabaseEntityRepository.findSeatDatabaseEntities(flightId, fareId);
    return SeatMapping.convertDatabaseToEntity(seatDatabaseEntities);
  }


}
