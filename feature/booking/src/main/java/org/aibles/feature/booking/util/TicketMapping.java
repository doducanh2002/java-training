package org.aibles.feature.booking.util;

import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.framework.datasource.TicketDatabaseEntity;

public class TicketMapping {

  public static Ticket convertDatabaseToEntity(TicketDatabaseEntity databaseEntity) {
    Ticket ticket = new Ticket();
    ticket.setId(databaseEntity.getId());
    ticket.setConfirmationCode(databaseEntity.getConfirmationCode());
    ticket.setNumOfPassenger(databaseEntity.getNumOfPassenger());
    ticket.setCustomerId(databaseEntity.getCustomerId());
    ticket.setTripType(databaseEntity.getTripType());
    ticket.setPromotion(PromotionMapping.convertIdToPromotion(databaseEntity.getPromoId()));
    return ticket;
  }

  public static TicketDatabaseEntity convertEntityToDatabase(Ticket ticket) {
    TicketDatabaseEntity databaseEntity = new TicketDatabaseEntity();
    databaseEntity.setId(ticket.getId());
    databaseEntity.setConfirmationCode(ticket.getConfirmationCode());
    databaseEntity.setNumOfPassenger(ticket.getNumOfPassenger());
    databaseEntity.setCustomerId(ticket.getCustomerId());
    databaseEntity.setTripType(ticket.getTripType());
    databaseEntity.setPromoId(ticket.getPromotion().getId());
    return databaseEntity;
  }
}
