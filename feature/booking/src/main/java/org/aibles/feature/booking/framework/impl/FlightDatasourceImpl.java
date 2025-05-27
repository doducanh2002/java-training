package org.aibles.feature.booking.framework.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.framework.datasource.FlightDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.FlightPlaneProjection;
import org.aibles.feature.booking.framework.impl.projection.FlightStatusProjection;
import org.aibles.feature.booking.framework.repository.FlightDatabaseEntityRepository;
import org.aibles.feature.booking.framework.repository.PlaneDatabaseEntityRepository;
import org.aibles.feature.booking.util.BenefitMapping;
import org.aibles.feature.booking.util.FlightMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FlightDatasourceImpl implements FlightRepository {
  private final FlightDatabaseEntityRepository flightDatabaseEntityRepository;

  private final PlaneDatabaseEntityRepository planeDatabaseEntityRepository;

  @Override
  public List<Flight> findAll(String from, String to, LocalDate date) {

    List<FlightStatusProjection> data = flightDatabaseEntityRepository.findAll(from, to, date);
    List<Flight> flights = new ArrayList<>();

    for (FlightStatusProjection flightRecord : data) {
      Flight flight = FlightMapping.convertToEntity(flightRecord);
      flights.add(flight);
    }
    return flights;
  }

  @Override
  public List<Flight> findDetailByTripId(String tripId) {
    List<FlightPlaneProjection> projections = flightDatabaseEntityRepository.findAllByTripId(tripId);
    log.info("(findDetailByTripId) Projections for trip {}: {}", tripId, projections);
    return projections.stream().map(projection -> {
      Flight flight = FlightMapping.convertToEntity(projection);
      log.info("(findDetailByTripId) Mapped flight: {}", flight);
      List<Benefits> benefitsEntity = planeDatabaseEntityRepository
          .findBenefitsById(flight.getPlane().getId()).stream()
          .map(BenefitMapping::convertDatabaseToEntity).toList();
      flight.getPlane().setBenefits(benefitsEntity);
      return flight;
    }).toList();
  }

  @Override
  public boolean existsById(String id) {
    return flightDatabaseEntityRepository.existsById(id);
  }

  @Transactional
  @Override
  public void saveAll(List<Flight> flightList) {
    flightDatabaseEntityRepository.saveAll(FlightMapping.convertEntityToDatabase(flightList));
  }

  @Override
  public Optional<Flight> findByFlightNumber(String flightNumber) {
    Optional<FlightDatabaseEntity> databaseEntity = flightDatabaseEntityRepository.findFirstByFlightNumber(
        flightNumber);
    return databaseEntity.map(FlightMapping::convertDatabaseToEntity);
  }

  @Override
  public List<String> findFlightIdByTicketId(String ticketId) {
    return flightDatabaseEntityRepository.findFlightIdByTicketId(ticketId);
  }

  @Override
  public Optional<FlightDatabaseEntity> findByFlightId(String flightId) {
    return flightDatabaseEntityRepository.findById(flightId);
  }

  @Override
  public void updateFlightStatus(String flightId, String status) {
    flightDatabaseEntityRepository.updateFlightStatus(flightId, status);
    log.info("Updated flight status to {} for flight ID: {}", status, flightId);
  }

}
