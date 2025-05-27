package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.AvailabilitySeatResponse;

public interface CheckFlightSeatAvailabilityUseCase {

  AvailabilitySeatResponse invoke(String flightId, List<String> seats);
}
