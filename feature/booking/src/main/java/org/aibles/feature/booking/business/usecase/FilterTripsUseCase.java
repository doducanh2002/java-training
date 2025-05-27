package org.aibles.feature.booking.business.usecase;

import java.time.LocalDate;
import java.util.List;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;

public interface FilterTripsUseCase {

  List<FilterTripsResponse> invoke(String departureAirportId, String arrivalAirportId, LocalDate date);
}
