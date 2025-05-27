package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.entity.ReservedSeat;
import org.aibles.feature.booking.entity.ReservedTrip;
import org.aibles.feature.booking.entity.Seat;
import org.aibles.feature.booking.framework.datasource.PassengerInfoDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.ReservedSeatDatabaseEntity;

public class ReservedSeatMapping {

  public static ReservedSeat convertDatabaseToEntity(ReservedSeatDatabaseEntity databaseEntity) {
    ReservedSeat reservedSeat = new ReservedSeat();
    reservedSeat.setId(databaseEntity.getId());
    ReservedTrip reservedTrip = new ReservedTrip();
    reservedTrip.setId(databaseEntity.getId());
    reservedSeat.setReservedTrip(reservedTrip);
    Seat seat = new Seat();
    seat.setId(databaseEntity.getSeatId());
    reservedSeat.setSeat(seat);
    return reservedSeat;
  }

  public static List<ReservedSeat> convertDatabaseToEntity(
      List<ReservedSeatDatabaseEntity> reservedSeatDatabaseEntities) {
    List<ReservedSeat> reservedSeats = new ArrayList<>();
    for (ReservedSeatDatabaseEntity databaseEntity : reservedSeatDatabaseEntities) {
      ReservedSeat reservedSeat = ReservedSeatMapping.convertDatabaseToEntity(databaseEntity);
      reservedSeats.add(reservedSeat);
    }
    return reservedSeats;
  }

  public static ReservedSeatDatabaseEntity convertEntityToDatabase(ReservedSeat reservedSeat) {
    ReservedSeatDatabaseEntity databaseEntity = new ReservedSeatDatabaseEntity();
    databaseEntity.setId(reservedSeat.getId());
    databaseEntity.setReservedTripId(reservedSeat.getReservedTrip().getId());
    databaseEntity.setSeatId(reservedSeat.getSeat().getId());
    return databaseEntity;
  }

  public static List<ReservedSeatDatabaseEntity> convertEntityToDatabase(
      List<ReservedSeat> reservedSeats) {
    List<ReservedSeatDatabaseEntity> databaseEntities = new ArrayList<>();
    for (ReservedSeat reservedSeat : reservedSeats) {
      ReservedSeatDatabaseEntity databaseEntity = ReservedSeatMapping.convertEntityToDatabase(
          reservedSeat);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }
}
