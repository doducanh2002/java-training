package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Seat;
import org.aibles.feature.booking.framework.controller.dto.response.SeatStatusDTO;
import org.aibles.feature.booking.framework.datasource.SeatDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.SeatStatusProjection;

public class SeatMapping {

  public static SeatDatabaseEntity convertEntityToDatabase(Seat seat) {
    SeatDatabaseEntity databaseEntity = new SeatDatabaseEntity();
    databaseEntity.setId(seat.getId());
    databaseEntity.setSeatRow(seat.getSeatRow());
    databaseEntity.setSeatColumn(seat.getSeatColumn());
    databaseEntity.setSeatPitch(seat.getSeatPitch());
    databaseEntity.setSeatWidth(seat.getSeatWidth());
    databaseEntity.setPlaneTravelClassId(seat.getPlaneTravelClass().getId());
    return databaseEntity;
  }

  public static Seat convertDatabaseToEntity(SeatDatabaseEntity databaseEntity) {
    Seat seat = new Seat();
    seat.setId(databaseEntity.getId());
    seat.setSeatRow(databaseEntity.getSeatRow());
    seat.setSeatColumn(databaseEntity.getSeatColumn());
    seat.setSeatPitch(databaseEntity.getSeatPitch());
    seat.setSeatWidth(databaseEntity.getSeatWidth());
    return seat;
  }

  public static List<SeatDatabaseEntity> convertEntityToDatabase(List<Seat> seats) {
    List<SeatDatabaseEntity> databaseEntities = new ArrayList<SeatDatabaseEntity>();
    for (Seat seat : seats) {
      SeatDatabaseEntity databaseEntity = convertEntityToDatabase(seat);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }

  public static List<Seat> convertDatabaseToEntity(List<SeatDatabaseEntity> databaseEntities) {
    List<Seat> seats = new ArrayList<>();
    for (SeatDatabaseEntity databaseEntity : databaseEntities) {
      Seat seat = convertDatabaseToEntity(databaseEntity);
      seats.add(seat);
    }
    return seats;
  }

  public static SeatStatusDTO convertProjectionIntoDTO(SeatStatusProjection projection) {
    SeatStatusDTO response = new SeatStatusDTO();
    response.setId(projection.getSeatId());
    response.setSeatRow(projection.getSeatRow());
    response.setSeatColumn(projection.getSeatColumn());
    response.setSeatPitch(projection.getSeatPitch());
    response.setSeatWidth(projection.getSeatWidth());
    return response;
  }

  public static List<SeatStatusDTO> convertProjectionsIntoDTOs(
      List<SeatStatusProjection> projections) {
    List<SeatStatusDTO> list = new ArrayList<>();
    for (SeatStatusProjection projection : projections) {
      list.add(convertProjectionIntoDTO(projection));
    }
    return list;
  }
}
