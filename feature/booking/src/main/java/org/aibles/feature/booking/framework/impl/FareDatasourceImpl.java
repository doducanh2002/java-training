package org.aibles.feature.booking.framework.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.business.dto.response.SeatStatusResponse;
import org.aibles.feature.booking.constant.CacheConstant;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.framework.controller.dto.response.SeatStatusDTO;
import org.aibles.feature.booking.framework.datasource.FareDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.SeatStatusProjection;
import org.aibles.feature.booking.framework.repository.FareDatabaseEntityRepository;
import org.aibles.feature.booking.framework.repository.ReservedSeatDatabaseEntityRepository;
import org.aibles.feature.booking.util.FareMapping;
import org.aibles.feature.booking.util.SeatMapping;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.exceptions.JedisException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FareDatasourceImpl implements FareRepository {

  private final FareDatabaseEntityRepository fareDatabaseEntityRepository;
  private final ReservedSeatDatabaseEntityRepository reservedSeatDatabaseEntityRepository;
  private final BookingCacheRepository bookingCacheRepository;

  @Override
  public Fare save(Fare fare) {
    FareDatabaseEntity entity = fareDatabaseEntityRepository.save(FareMapping.convertEntityToDatabase(fare));
    return FareMapping.convertDatabaseToEntity(entity);
  }

  @Override
  public Optional<Fare> findByName(String name) {
    Optional<FareDatabaseEntity> databaseEntity = fareDatabaseEntityRepository.findByName(name);
    return databaseEntity.map(FareMapping::convertDatabaseToEntity);
  }
  @Override
  public List<SeatStatusResponse> getPlaneFareStatus(String flightId, String fareId) {

    List<SeatStatusProjection> projections = fareDatabaseEntityRepository.findByFlightIdAndFareId(
        flightId, fareId);

    Set<String> reservedSeatIds;

    try {
      reservedSeatIds = getReservedSeatIdsFromCache(flightId);
    } catch (JedisException | SerializationException e) {
      reservedSeatIds = getReservedSeatIdsFromDatabase(flightId);
    }

    List<SeatStatusDTO> seatStatusDTOS = new ArrayList<>(
        SeatMapping.convertProjectionsIntoDTOs(projections));

    for (SeatStatusDTO seatStatusDTO : seatStatusDTOS) {
      seatStatusDTO.setReserved(reservedSeatIds.contains(seatStatusDTO.getId()));
    }

    return SeatStatusResponse.from(seatStatusDTOS);
  }

  @Override
  public boolean existsById(String id) {
    return fareDatabaseEntityRepository.existsById(id);
  }

  @Override
  public List<Fare> findByIds(List<String> ids) {
    return fareDatabaseEntityRepository.findByIds(ids).stream().map(
        FareMapping::convertDatabaseToEntity
    ).toList();
  }

  private Set<String> getReservedSeatIdsFromCache(String flightId) {
    return bookingCacheRepository.members(CacheConstant.RESERVED_SEAT_KEY + "_" + flightId, String.class);
  }

  private Set<String> getReservedSeatIdsFromDatabase(String flightId) {
    List<String> reservedSeatIds = reservedSeatDatabaseEntityRepository.getReservedSeatsByFlight(
        flightId);

    return new HashSet<>(reservedSeatIds);
  }
}
