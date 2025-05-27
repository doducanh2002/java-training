package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.PassengerAssistance;

public interface PassengerAssistanceRepository {

  void saveAll(List<PassengerAssistance> passengerAssistances);
}
