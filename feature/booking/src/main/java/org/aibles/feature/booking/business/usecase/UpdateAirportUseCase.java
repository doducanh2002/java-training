package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.UpdateAirportUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.AirportResponse;

public interface UpdateAirportUseCase {

  AirportResponse invoke(String airportId, UpdateAirportUseCaseRequest request);
}
