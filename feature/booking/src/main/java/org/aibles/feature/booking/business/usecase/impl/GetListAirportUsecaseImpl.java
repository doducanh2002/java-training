package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.business.dto.response.AirportResponse;
import org.aibles.feature.booking.business.usecase.GetListAirportUsecase;
import org.aibles.feature.booking.entity.Airport;

import java.util.ArrayList;
import java.util.List;

public class GetListAirportUsecaseImpl implements GetListAirportUsecase {
  private final AirportRepository airportRepository;

  public GetListAirportUsecaseImpl(AirportRepository airportRepository) {
    this.airportRepository = airportRepository;
  }

  @Override
  public List<AirportResponse> invoke(String country, String region) {
    List<Airport> airports = airportRepository.getAirportList(country, region);
    List<AirportResponse> airportResponses = new ArrayList<>();
    for(Airport airport : airports) {
      AirportResponse airportResponse = AirportResponse.from(airport);
      airportResponses.add(airportResponse);
    }
    return airportResponses;
  }
}
