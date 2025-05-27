package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PassengerInfoRepository;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.framework.repository.PassengerInfoDatabaseEntityRepository;
import org.aibles.feature.booking.util.PassengerMapping;
import org.aibles.feature.booking.util.PassengerInfoMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PassengerInfoDatasourceImpl implements PassengerInfoRepository {

  private final PassengerInfoDatabaseEntityRepository passengerInfoDatabaseEntityRepository;

  @Override
  public boolean existsByTicketIdAndLastName(String ticketId, String lastName) {
    return passengerInfoDatabaseEntityRepository.existsByTicketIdAndLastName(ticketId, lastName);
  }

  @Override
  public List<PassengerInfo> getPassengerInfoByTicketId(String ticketId) {
    return passengerInfoDatabaseEntityRepository.findByTicketId(ticketId).stream()
            .map(PassengerMapping::convertDatabaseToEntity).toList();

  }

  @Override
  @Transactional
  public List<PassengerInfo> saveAll(List<PassengerInfo> passengerInfos) {
    return PassengerInfoMapping.convertDatabaseToEntity(
        passengerInfoDatabaseEntityRepository.saveAll(
            PassengerInfoMapping.convertEntityToDatabase(passengerInfos)));
  }
}
