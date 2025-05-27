package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Payment;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.framework.datasource.PaymentDatabaseEntity;

public class PaymentMapping {

  public static Payment convertDatabaseToEntity(PaymentDatabaseEntity databaseEntity) {
    Payment payment = new Payment();
    payment.setId(databaseEntity.getId());
    payment.setAmount(databaseEntity.getAmount());
    payment.setPaymentType(databaseEntity.getPaymentType());
    payment.setPaymentStatus(databaseEntity.getPaymentStatus());
    Ticket ticket = new Ticket();
    ticket.setId(databaseEntity.getId());
    payment.setTicket(ticket);
    return payment;
  }

  public static List<Payment> convertDatabaseToEntity(
      List<PaymentDatabaseEntity> paymentDatabaseEntities) {
    List<Payment> payments = new ArrayList<>();
    for (PaymentDatabaseEntity databaseEntity : paymentDatabaseEntities) {
      Payment payment = PaymentMapping.convertDatabaseToEntity(databaseEntity);
      payments.add(payment);
    }
    return payments;
  }

  public static PaymentDatabaseEntity convertEntityToDatabase(Payment payment) {
    PaymentDatabaseEntity databaseEntity = new PaymentDatabaseEntity();
    databaseEntity.setId(payment.getId());
    databaseEntity.setAmount(payment.getAmount());
    databaseEntity.setPaymentType(payment.getPaymentType());
    databaseEntity.setPaymentStatus(payment.getPaymentStatus());
    databaseEntity.setTicketId(payment.getTicket().getId());
    return databaseEntity;
  }
}
