package org.aibles.feature.booking.framework.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.adapter.repository.PassengerAssistanceRepository;
import org.aibles.feature.booking.entity.PassengerAssistance;
import org.aibles.feature.booking.framework.repository.PassengerAssistanceDatabaseEntityRepository;
import org.aibles.feature.booking.util.PassengerAssistanceMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PassengerAssistanceDatasourceImpl implements PassengerAssistanceRepository {

  private final PassengerAssistanceDatabaseEntityRepository passengerAssistanceDatabaseEntityRepository;

  @Override
  @Transactional
  public void saveAll(List<PassengerAssistance> passengerAssistances) {
    passengerAssistanceDatabaseEntityRepository.saveAll(
            PassengerAssistanceMapping.convertEntityToDatabase(passengerAssistances));
  }
}
