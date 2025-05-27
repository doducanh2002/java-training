package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Airport;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.Trip;
import org.aibles.feature.booking.framework.datasource.AirportDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.FlightDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.FlightPlaneProjection;
import org.aibles.feature.booking.framework.impl.projection.FlightStatusProjection;

public class FlightMapping {
  public static Flight convertDatabaseToEntity(
      FlightDatabaseEntity databaseEntity, AirportDatabaseEntity airportDatabaseEntity) {
    Flight flight = new Flight();
    flight.setId(databaseEntity.getId());
    flight.setFlightNumber(databaseEntity.getflightNumber());
    flight.setAirline(databaseEntity.getAirline());
    flight.setDepartureTime(databaseEntity.getDepartureTime());
    flight.setArrivalTime(databaseEntity.getArrivalTime());
    flight.setIsStopStation(databaseEntity.getIsStopStation());
    flight.setMiles(databaseEntity.getMiles());
    flight.setStatus(databaseEntity.getStatus());

    Airport airport = AirportMapping.convertDatabaseToEntity(airportDatabaseEntity);
    flight.setDepartureAirport(airport);
    flight.setArrivalAirport(airport);
    return flight;
  }

  public static Flight convertToEntity(FlightStatusProjection flightStatusProjection) {
    Flight flight = new Flight();
    flight.setId(flightStatusProjection.getId());
    flight.setFlightNumber(flightStatusProjection.getFlightNumber());
    flight.setAirline(flightStatusProjection.getAirline());
    flight.setDepartureTime(flightStatusProjection.getDepartureTime());
    flight.setArrivalTime(flightStatusProjection.getArrivalTime());
    flight.setIsStopStation(flightStatusProjection.isStopStation());
    flight.setMiles(flightStatusProjection.getMiles());
    flight.setStatus(flightStatusProjection.getStatus());

    Airport departureAirport = new Airport();
    departureAirport.setId(flightStatusProjection.getDepartureAirport().getId());
    departureAirport.setCode(flightStatusProjection.getDepartureAirport().getCode());
    departureAirport.setCountry(flightStatusProjection.getDepartureAirport().getCountry());
    departureAirport.setName(flightStatusProjection.getDepartureAirport().getName());
    departureAirport.setRegion(flightStatusProjection.getDepartureAirport().getRegion());

    Airport arrivalAirport = new Airport();
    arrivalAirport.setId(flightStatusProjection.getArrivalAirport().getId());
    arrivalAirport.setCode(flightStatusProjection.getArrivalAirport().getCode());
    arrivalAirport.setCountry(flightStatusProjection.getArrivalAirport().getCountry());
    arrivalAirport.setName(flightStatusProjection.getArrivalAirport().getName());
    arrivalAirport.setRegion(flightStatusProjection.getArrivalAirport().getRegion());

    flight.setDepartureAirport(departureAirport);
    flight.setArrivalAirport(arrivalAirport);
    return flight;
  }

  public static Flight convertToEntity(FlightPlaneProjection projection) {
    Flight flight = new Flight();
    flight.setId(projection.getFlightId());
    flight.setFlightNumber(projection.getFlightNumber());
    flight.setAirline(projection.getAirline());
    flight.setDepartureTime(projection.getDepartureTime());
    flight.setArrivalTime(projection.getArrivalTime());
    flight.setIsStopStation(projection.isStopStation());
    flight.setMiles(projection.getMiles());
    flight.setStatus(projection.getStatus());

    Trip trip = new Trip();
    trip.setId(projection.getTripId());
    flight.setTrip(trip);

    Airport departureAirport = new Airport();
    departureAirport.setName(projection.getDepartureAirportName());

    Airport arrivalAirport = new Airport();
    arrivalAirport.setName(projection.getArrivalAirportName());

    Plane plane = new Plane();
    plane.setId(projection.getPlaneId());
    plane.setModel(projection.getModel());

    flight.setDepartureAirport(departureAirport);
    flight.setArrivalAirport(arrivalAirport);
    flight.setPlane(plane);

    return flight;
  }

  public static FlightDatabaseEntity convertEntityToDatabase(Flight flights) {
    FlightDatabaseEntity databaseEntity = new FlightDatabaseEntity();
    databaseEntity.setId(flights.getId());
    databaseEntity.setflightNumber(flights.getFlightNumber());
    databaseEntity.setAirline(flights.getAirline());
    databaseEntity.setDepartureTime(flights.getDepartureTime());
    databaseEntity.setArrivalTime(flights.getArrivalTime());
    databaseEntity.setIsStopStation(flights.getIsStopStation());
    databaseEntity.setMiles(flights.getMiles());
    databaseEntity.setStatus(flights.getStatus());
    databaseEntity.setArrivalAirportId(flights.getArrivalAirport().getId());
    databaseEntity.setDepartureAirportId(flights.getDepartureAirport().getId());
    databaseEntity.setPlaneId(flights.getPlane().getId());
    databaseEntity.setTripId(flights.getTrip().getId());
    return databaseEntity;
  }

  public static List<FlightDatabaseEntity> convertEntityToDatabase(List<Flight> flights) {
    List<FlightDatabaseEntity> databaseEntities = new ArrayList<>();
    for (Flight flight : flights) {
      FlightDatabaseEntity databaseEntity = FlightMapping.convertEntityToDatabase(flight);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }

  public static Flight convertDatabaseToEntity(FlightDatabaseEntity databaseEntity) {
    Flight flight = new Flight();
    flight.setId(databaseEntity.getId());
    flight.setFlightNumber(databaseEntity.getflightNumber());
    flight.setAirline(databaseEntity.getAirline());
    flight.setDepartureTime(databaseEntity.getDepartureTime());
    flight.setArrivalTime(databaseEntity.getArrivalTime());
    flight.setIsStopStation(databaseEntity.getIsStopStation());
    flight.setMiles(databaseEntity.getMiles());
    flight.setStatus(databaseEntity.getStatus());
    Airport departureAirport = new Airport();
    departureAirport.setId(databaseEntity.getDepartureAirportId());
    Airport arrivalAirport = new Airport();
    arrivalAirport.setId(databaseEntity.getDepartureAirportId());
    flight.setDepartureAirport(departureAirport);
    flight.setArrivalAirport(arrivalAirport);
    Plane plane = new Plane();
    plane.setId(databaseEntity.getPlaneId());
    flight.setPlane(plane);
    Trip trip = new Trip();
    trip.setId(databaseEntity.getTripId());
    flight.setTrip(trip);
    return flight;
  }

}
