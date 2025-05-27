package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.entity.Airport;

public interface AirportRepository {
  List<Airport> getAirportList(String country, String region);

  String findCountryByRegion(String region);

  boolean existByAirportId(String airportId);
  Optional<Airport> findByCode(String code);
  void saveAll(List<Airport> airportList);

  Airport save(Airport airport);

  Optional<Airport> findById(String airportId);
}
