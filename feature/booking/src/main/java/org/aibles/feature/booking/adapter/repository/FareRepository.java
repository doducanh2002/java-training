package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.business.dto.response.SeatStatusResponse;
import org.aibles.feature.booking.entity.Fare;

public interface FareRepository {

  Fare save(Fare fare);
  Optional<Fare> findByName(String name);

  List<SeatStatusResponse> getPlaneFareStatus(String flightId, String fareId);
  boolean existsById(String id);
  List<Fare> findByIds(List<String> ids);
}
