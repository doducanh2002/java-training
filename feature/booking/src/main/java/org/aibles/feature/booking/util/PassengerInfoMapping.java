package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.framework.datasource.PassengerInfoDatabaseEntity;

public class PassengerInfoMapping {

  public static PassengerInfo convertDatabaseToEntity(PassengerInfoDatabaseEntity databaseEntity) {
    PassengerInfo passengerInfo = new PassengerInfo();
    passengerInfo.setId(databaseEntity.getId());
    passengerInfo.setFirstName(databaseEntity.getFirstName());
    passengerInfo.setMiddleName(databaseEntity.getMiddleName());
    passengerInfo.setLastName(databaseEntity.getLastName());
    passengerInfo.setDateOfBirth(databaseEntity.getDateOfBirth());
    passengerInfo.setGender(databaseEntity.getGender());
    passengerInfo.setCountry(databaseEntity.getCountry());
    Ticket ticket = new Ticket();
    ticket.setId(databaseEntity.getTicketId());
    passengerInfo.setTicket(ticket);
    return passengerInfo;
  }

  public static List<PassengerInfo> convertDatabaseToEntity(
      List<PassengerInfoDatabaseEntity> databaseEntities) {
    List<PassengerInfo> passengerInfos = new ArrayList<>();
    for (PassengerInfoDatabaseEntity databaseEntity : databaseEntities) {
      PassengerInfo passengerInfo = PassengerInfoMapping.convertDatabaseToEntity(databaseEntity);
      passengerInfos.add(passengerInfo);
    }
    return passengerInfos;
  }

  public static PassengerInfoDatabaseEntity convertEntityToDatabase(PassengerInfo passengerInfo) {
    PassengerInfoDatabaseEntity databaseEntity = new PassengerInfoDatabaseEntity();
    databaseEntity.setId(passengerInfo.getId());
    databaseEntity.setFirstName(passengerInfo.getFirstName());
    databaseEntity.setMiddleName(passengerInfo.getMiddleName());
    databaseEntity.setLastName(passengerInfo.getLastName());
    databaseEntity.setDateOfBirth(passengerInfo.getDateOfBirth());
    databaseEntity.setGender(passengerInfo.getGender());
    databaseEntity.setCountry(passengerInfo.getCountry());
    databaseEntity.setTicketId(passengerInfo.getTicket().getId());
    return databaseEntity;
  }

  public static List<PassengerInfoDatabaseEntity> convertEntityToDatabase(
      List<PassengerInfo> passengerInfos) {
    List<PassengerInfoDatabaseEntity> databaseEntities = new ArrayList<>();
    for (PassengerInfo passengerInfo : passengerInfos) {
      PassengerInfoDatabaseEntity databaseEntity = PassengerInfoMapping.convertEntityToDatabase(
          passengerInfo);
      databaseEntities.add(databaseEntity);
    }
    return databaseEntities;
  }
}
