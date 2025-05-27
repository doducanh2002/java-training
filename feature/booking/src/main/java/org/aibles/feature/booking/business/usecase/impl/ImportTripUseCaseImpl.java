package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportTripDTO;
import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.MealRepository;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.exception.AirportCodeNotFoundException;
import org.aibles.feature.booking.business.exception.DuplicateFlightNumberException;
import org.aibles.feature.booking.business.exception.NamePlaneNotFoundException;
import org.aibles.feature.booking.business.usecase.ImportTripUseCase;
import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.entity.Meal;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.Trip;
import org.aibles.framework.error_handle.ImportFileExcelErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportTripUseCaseImpl implements ImportTripUseCase {

  private final ExcelHelper excelHelper;
  private final TripRepository tripRepository;
  private final AirportRepository airportRepository;
  private final PlaneRepository planeRepository;
  private final MealRepository mealRepository;
  private final FlightRepository flightRepository;
  private static final Logger log = LoggerFactory.getLogger(
      ImportTripUseCaseImpl.class);

  public ImportTripUseCaseImpl(ExcelHelper excelHelper, TripRepository tripRepository,
      AirportRepository airportRepository, PlaneRepository planeRepository,
      MealRepository mealRepository, FlightRepository flightRepository) {
    this.excelHelper = excelHelper;
    this.tripRepository = tripRepository;
    this.airportRepository = airportRepository;
    this.planeRepository = planeRepository;
    this.mealRepository = mealRepository;
    this.flightRepository = flightRepository;
  }

  @Override
  public void invoke(InputStream inputStream) {
    List<ImportTripDTO> importTrips = excelHelper.importTrip(inputStream);
    log.info("(invoke)importTrips: {}", importTrips);
    List<Trip> tripsToSave = new ArrayList<>();
    List<Flight> flightsToSave = new ArrayList<>();
    List<Meal> mealsToSave = new ArrayList<>();

    Map<String, Trip> tripMap = new HashMap<>();
    Map<String, Airport> airportMap = new HashMap<>();
    Map<String, Plane> planeMap = new HashMap<>();
    Map<String, Map<String, Flight>> tripFlightMap = new HashMap<>();

    for (ImportTripDTO importTrip: importTrips) {
      handleAirportMap(importTrip.getArrivalAirportCode(), airportMap);
      handleAirportMap(importTrip.getDepartureAirportCode(), airportMap);
      handlePlaneMap(importTrip.getPlane(), planeMap);

      TripRecord tripRecord = handleTripRecord(
          importTrip, tripMap, tripFlightMap, tripsToSave);

      handleFlightRecord(importTrip, tripFlightMap, tripRecord, airportMap, planeMap, flightsToSave);
      handleMealRecord(importTrip, tripFlightMap, mealsToSave);
    }

    if (isDuplicateFlightNumber(tripFlightMap)) {
      throw new DuplicateFlightNumberException();
    }

    tripRepository.saveAll(tripsToSave);
    flightRepository.saveAll(flightsToSave);
    mealRepository.saveAll(mealsToSave);
  }

  private static void handleMealRecord(ImportTripDTO importTrip,
      Map<String, Map<String, Flight>> tripFlightMap,
      List<Meal> mealsToSave) {
    if (importTrip.getDish() == null || importTrip.getDish().isEmpty() ||
        importTrip.getMealTime() == null ||
        importTrip.getPrice() == null) {
      return;
    }
    Meal meal = new Meal();
    meal.setDish(importTrip.getDish());
    meal.setPrice(importTrip.getPrice());
    meal.setMealTime(importTrip.getMealTime());
    meal.setFlight(tripFlightMap.get(importTrip.getId())
        .get(importTrip.getFlightNumber()));
    mealsToSave.add(meal);
  }

  private TripRecord handleTripRecord(ImportTripDTO importTrip, Map<String, Trip> tripMap,
      Map<String, Map<String, Flight>> tripFlightMap, List<Trip> tripsToSave) {
    String departure = importTrip.getDeparture().toLowerCase();
    String arrival = importTrip.getArrival().toLowerCase();
    LocalDateTime startDate = importTrip.getStartDate();
    LocalDateTime endDate = importTrip.getEndDate();

    if (isInvalidFlyTime(startDate, endDate)) {
      throw new ImportFileExcelErrorException();
    }

    Trip tripEntity = tripMap.get(importTrip.getId());
    if (tripEntity == null) {
      Optional<Trip> tripOptional = tripRepository.findById(importTrip.getId());
      if (tripOptional.isEmpty()) {
        Trip trip = new Trip();
        trip.setId(importTrip.getId());
        setTrip(importTrip, trip);
        tripEntity = trip;
        tripMap.put(importTrip.getId(), tripEntity);
        tripFlightMap.put(importTrip.getId(), new HashMap<>());
      } else {
        tripEntity = tripOptional.get();
        setTrip(importTrip, tripEntity);
        tripMap.put(importTrip.getId(), tripEntity);
        tripFlightMap.put(importTrip.getId(), new HashMap<>());
      }
      tripsToSave.add(tripEntity);
    }
    return new TripRecord(departure, arrival, tripEntity);
  }

  private static void setTrip(ImportTripDTO importTrip, Trip trip) {
    trip.setStartDate(importTrip.getStartDate());
    trip.setEndDate(importTrip.getEndDate());
    trip.setDeparture(importTrip.getDeparture());
    trip.setArrival(importTrip.getArrival());
  }

  private void handleFlightRecord(ImportTripDTO importTrip, Map<String, Map<String, Flight>> tripFlightMap,
      TripRecord tripRecord, Map<String, Airport> airportMap, Map<String, Plane> planeMap,
      List<Flight> flightsToSave) {
    LocalDateTime departureTime = importTrip.getDepartureTime();
    LocalDateTime arrivalTime = importTrip.getArrivalTime();
    if (isInvalidFlyTime(departureTime, arrivalTime)) {
      throw new ImportFileExcelErrorException();
    }
    Flight flightEntity = tripFlightMap.get(importTrip.getId()).get(importTrip.getFlightNumber());
    if (flightEntity == null) {
      Optional<Flight> existFlight = flightRepository.findByFlightNumber(importTrip.getFlightNumber());
      if (existFlight.isPresent()) {
        flightEntity = existFlight.get();
        log.info("(handleFlightRecord)set exist flight: {}", flightEntity.getFlightNumber());
        setFlight(importTrip, tripRecord, airportMap, planeMap, flightEntity, departureTime,
            arrivalTime);
      } else {
        flightEntity = new Flight();
        flightEntity.setId(UUID.randomUUID().toString());
        setFlight(importTrip, tripRecord, airportMap, planeMap, flightEntity, departureTime,
            arrivalTime);
      }
      flightsToSave.add(flightEntity);
      Map<String, Flight> flightMap = tripFlightMap.get(importTrip.getId());

      flightMap.put(importTrip.getFlightNumber(), flightEntity);

      tripFlightMap.put(importTrip.getId(), flightMap);
    }
  }

  private static void setFlight(ImportTripDTO importTrip, TripRecord tripRecord,
      Map<String, Airport> airportMap, Map<String, Plane> planeMap, Flight flightEntity,
      LocalDateTime departureTime, LocalDateTime arrivalTime) {
    flightEntity.setDepartureTime(departureTime);
    flightEntity.setArrivalTime(arrivalTime);
    flightEntity.setDepartureAirport(airportMap.get(importTrip.getDepartureAirportCode()));
    flightEntity.setArrivalAirport(airportMap.get(importTrip.getArrivalAirportCode()));
    flightEntity.setFlightNumber(importTrip.getFlightNumber());
    flightEntity.setAirline(importTrip.getAirline());
    flightEntity.setMiles(importTrip.getMiles());
    flightEntity.setStatus(importTrip.getStatus());
    flightEntity.setIsStopStation(importTrip.getIsStopStation() == 1);
    flightEntity.setTrip(tripRecord.tripEntity());
    flightEntity.setPlane(planeMap.get(importTrip.getPlane()));
  }

  private static boolean isInvalidFlyTime(LocalDateTime startDate, LocalDateTime endDate) {
    return startDate.isBefore(LocalDateTime.now()) || endDate.isBefore(LocalDateTime.now()) ||
        startDate.isAfter(endDate);
  }

  private record TripRecord(String departure, String arrival, Trip tripEntity) {

  }

  private void handlePlaneMap(String plane, Map<String, Plane> planeMap) {
    if (plane == null) {
      throw new ImportFileExcelErrorException();
    }
    planeMap.computeIfAbsent(plane, v -> planeRepository
        .findByModel(plane)
        .orElseThrow(() -> new NamePlaneNotFoundException(plane)));
  }

  private void handleAirportMap(String importTrip, Map<String, Airport> airportMap) {
    if (importTrip == null) {
      throw new ImportFileExcelErrorException();
    }
    airportMap.computeIfAbsent(importTrip, v -> airportRepository
        .findByCode(importTrip)
        .orElseThrow(() -> new AirportCodeNotFoundException(importTrip)));
  }

  private boolean isDuplicateFlightNumber(Map<String, Map<String, Flight>> tripFlightMap) {
    List<String> flightNumberList = new ArrayList<>();

    tripFlightMap.forEach((k ,v) -> flightNumberList.addAll(v.keySet()));

    Set<String> flightNumberSet = new HashSet<>(flightNumberList);

    return flightNumberList.size() != flightNumberSet.size();
  }
}
