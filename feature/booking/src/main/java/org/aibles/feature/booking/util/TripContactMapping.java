package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.entity.TripContact;
import org.aibles.feature.booking.framework.datasource.TripContactDatabaseEntity;

public class TripContactMapping {

  public static TripContact convertDatabaseToEntity(TripContactDatabaseEntity databaseEntity) {
    TripContact tripContact = new TripContact();
    tripContact.setId(databaseEntity.getId());
    tripContact.setPrimaryEmail(databaseEntity.getPrimaryEmail());
    tripContact.setSecondaryEmail(databaseEntity.getSecondaryEmail());
    tripContact.setPhoneNumber(databaseEntity.getPhoneNumber());
    Ticket ticket = new Ticket();
    ticket.setId(databaseEntity.getId());
    tripContact.setTicket(ticket);
    return tripContact;
  }

  public static List<TripContact> convertDatabaseToEntity(
      List<TripContactDatabaseEntity> TripContactDatabaseEntities) {
    List<TripContact> tripContacts = new ArrayList<>();
    for (TripContactDatabaseEntity databaseEntity : TripContactDatabaseEntities) {
      TripContact tripContact = TripContactMapping.convertDatabaseToEntity(databaseEntity);
      tripContacts.add(tripContact);
    }
    return tripContacts;
  }

  public static TripContactDatabaseEntity convertEntityToDatabase(TripContact tripContact) {
    TripContactDatabaseEntity databaseEntity = new TripContactDatabaseEntity();
    databaseEntity.setId(tripContact.getId());
    databaseEntity.setPrimaryEmail(tripContact.getPrimaryEmail());
    databaseEntity.setSecondaryEmail(tripContact.getSecondaryEmail());
    databaseEntity.setPhoneNumber(tripContact.getPhoneNumber());
    databaseEntity.setTicketId(tripContact.getTicket().getId());
    return databaseEntity;
  }
}
