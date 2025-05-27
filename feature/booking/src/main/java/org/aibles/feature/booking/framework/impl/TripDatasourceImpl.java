package org.aibles.feature.booking.framework.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.entity.Trip;
import org.aibles.feature.booking.framework.datasource.TripDatabaseEntity;
import org.aibles.feature.booking.framework.repository.TripDatabaseEntityRepository;
import org.aibles.feature.booking.util.TripMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TripDatasourceImpl implements TripRepository {

  private final TripDatabaseEntityRepository tripDatabaseEntityRepository;


  @Override
  public List<Trip> findCheckInTripByTicketId(String ticketId) {
    return TripMapping.convertDatabaseToEntity(
        tripDatabaseEntityRepository.findByTicketId(ticketId));
  }

  @Override
  public List<Trip> findAllByAccountId(String accountId) {
    return TripMapping.convertDatabaseToEntity(
        tripDatabaseEntityRepository.getAllByAccountId(accountId));
  }

  @Override
  public List<Trip> findAllByAirportIdAndStartDate(String departureAirportId,
      String arrivalAirportId, LocalDate startDate) {
    return TripMapping.convertDatabaseToEntity(
        tripDatabaseEntityRepository.findAllByAirportIdAndStartDate(departureAirportId,
            arrivalAirportId, startDate)
    );
  }

  @Transactional
  @Override
  public Trip save(Trip trip) {
    return TripMapping.convertDatabaseToEntity(
        tripDatabaseEntityRepository.save(TripMapping.convertEntityToDatabase(trip)));
  }

  @Transactional
  @Override
  public void saveAll(List<Trip> tripList) {
    tripDatabaseEntityRepository.saveAll(TripMapping.convertEntityToDatabase(tripList));
  }

  @Override
  public boolean existsById(String id) {
    return tripDatabaseEntityRepository.existsById(id);
  }

  @Override
  public Optional<Trip> findById(String id) {
    Optional<TripDatabaseEntity> tripDatabaseEntityOptional =
        tripDatabaseEntityRepository.findById(id);
    if (tripDatabaseEntityOptional.isEmpty()) {
      return Optional.empty();
    }
    TripDatabaseEntity tripDatabaseEntity = tripDatabaseEntityOptional.get();
    return Optional.of(TripMapping.convertDatabaseToEntity(tripDatabaseEntity));
  }
}
