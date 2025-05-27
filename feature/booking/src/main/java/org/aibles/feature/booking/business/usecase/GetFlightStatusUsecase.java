package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.FlightStatusResponse;

import java.time.LocalDate;
import java.util.List;

public interface GetFlightStatusUsecase {
  List<FlightStatusResponse> invoke(String from, String to, LocalDate date);
}
