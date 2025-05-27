package org.aibles.feature.booking.framework.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.framework.datasource.AirportDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.BenefitDatabaseEntity;
import org.aibles.feature.booking.framework.repository.AirportDatabaseEntityRepository;
import org.aibles.feature.booking.util.AirportMapping;
import org.aibles.feature.booking.util.BenefitMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AirportDatasourceImpl implements AirportRepository {

  private final AirportDatabaseEntityRepository airportDatabaseEntityRepository;

  @Override
  public List<Airport> getAirportList(String country, String region) {
    List<AirportDatabaseEntity> airportDatabaseEntityList = new ArrayList<>();
    if (StringUtils.isNotBlank(country)) {
      if (StringUtils.isNotBlank(region)) {
        airportDatabaseEntityList =
            airportDatabaseEntityRepository.findAllByCountryAndRegion(country, region);
      } else {
        airportDatabaseEntityList = airportDatabaseEntityRepository.findAllByCountry(country);
      }
    }
    return AirportMapping.convertDatabaseToEntity(airportDatabaseEntityList);
  }

  @Override
  @Transactional
  public String findCountryByRegion(String region) {
    return airportDatabaseEntityRepository.findCountryByRegion(region);
  }

  @Override
  public boolean existByAirportId(String airportId) {
    return airportDatabaseEntityRepository.existsById(airportId);
  }

  @Override
  public Optional<Airport> findByCode(String code) {
    Optional<AirportDatabaseEntity> airportDatabaseEntity = airportDatabaseEntityRepository.findByCode(code);
    return airportDatabaseEntity.map(AirportMapping::convertDatabaseToEntity);
  }
  @Override
  public void saveAll(List<Airport> airportList) {
    List<AirportDatabaseEntity> airportDatabaseEntities = airportList.stream().map(AirportMapping::convertEntityToDatabase).toList();
    airportDatabaseEntityRepository.saveAll(airportDatabaseEntities);
  }

  @Override
  public Airport save(Airport airport) {
    AirportDatabaseEntity airportDatabaseEntity = AirportMapping.convertEntityToDatabase(airport);
    return AirportMapping.convertDatabaseToEntity(
        airportDatabaseEntityRepository.save(airportDatabaseEntity));
  }

  @Override
  public Optional<Airport> findById(String airportId) {
    Optional<AirportDatabaseEntity> airportDatabaseEntity = airportDatabaseEntityRepository.findById(airportId);
    return airportDatabaseEntity.map(AirportMapping::convertDatabaseToEntity);
  }
}
