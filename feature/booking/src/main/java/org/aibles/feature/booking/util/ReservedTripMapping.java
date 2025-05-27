package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.entity.ReservedTrip;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.entity.Trip;
import org.aibles.feature.booking.framework.datasource.PassengerInfoDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.ReservedTripDatabaseEntity;

public class ReservedTripMapping {

  public static ReservedTrip convertDatabaseToEntity(ReservedTripDatabaseEntity databaseEntity) {
    ReservedTrip reservedTrip = new ReservedTrip();
    reservedTrip.setId(databaseEntity.getId());
    Fare fare = new Fare();
    fare.setId(databaseEntity.getFareId());
    reservedTrip.setFare(fare);
    Trip trip = new Trip();
    trip.setId(databaseEntity.getTripId());
    reservedTrip.setTrip(trip);
    Ticket ticket = new Ticket();
    ticket.setId(databaseEntity.getTicketId());
    reservedTrip.setTicket(ticket);
    return reservedTrip;
  }

  public static List<ReservedTrip> convertDatabaseToEntity(
      List<ReservedTripDatabaseEntity> reservedTripDatabaseEntities) {
    List<ReservedTrip> reservedTrips = new ArrayList<>();
    for (ReservedTripDatabaseEntity databaseEntity : reservedTripDatabaseEntities) {
      ReservedTrip reservedTrip = ReservedTripMapping.convertDatabaseToEntity(databaseEntity);
      reservedTrips.add(reservedTrip);
    }
    return reservedTrips;
  }

  public static ReservedTripDatabaseEntity convertEntityToDatabase(ReservedTrip reservedTrip) {
    ReservedTripDatabaseEntity databaseEntity = new ReservedTripDatabaseEntity();
    databaseEntity.setId(reservedTrip.getId());
    databaseEntity.setFareId(reservedTrip.getFare().getId());
    databaseEntity.setTripId(reservedTrip.getTrip().getId());
    databaseEntity.setTicketId(reservedTrip.getTicket().getId());
    return databaseEntity;
  }

  public static List<ReservedTripDatabaseEntity> convertEntityToDatabase(
      List<ReservedTrip> reservedTrips) {
    List<ReservedTripDatabaseEntity> databaseEntities = new ArrayList<>();
    for (ReservedTrip reservedTrip : reservedTrips) {
      ReservedTripDatabaseEntity databaseEntity = ReservedTripMapping.convertEntityToDatabase(
          reservedTrip);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }
}
