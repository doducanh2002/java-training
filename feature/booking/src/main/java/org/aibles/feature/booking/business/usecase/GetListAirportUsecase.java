package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.AirportResponse;

import java.util.List;

public interface GetListAirportUsecase {

  List<AirportResponse> invoke(String country, String region);
}
