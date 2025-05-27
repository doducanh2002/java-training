package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.AirportDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDatabaseEntityRepository
    extends JpaRepository<AirportDatabaseEntity, String> {
  List<AirportDatabaseEntity> findAllByCountry(String country);

  List<AirportDatabaseEntity> findAllByCountryAndRegion(String country, String region);

  @Query(value = "SELECT DISTINCT a.country FROM AirportDatabaseEntity a WHERE a.region = :region")
  String findCountryByRegion(String region);

  Optional<AirportDatabaseEntity> findByCode(String code);
}
