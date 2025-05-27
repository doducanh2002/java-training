package org.aibles.feature.booking.framework.impl.projection;

import java.time.LocalDateTime;

public interface FlightStatusProjection {
  String getId();

  String getFlightNumber();

  String getAirline();

  AirportProjection getDepartureAirport();

  AirportProjection getArrivalAirport();

  LocalDateTime getDepartureTime();

  LocalDateTime getArrivalTime();

  boolean isStopStation();

  Long getMiles();

  String getStatus();
}
