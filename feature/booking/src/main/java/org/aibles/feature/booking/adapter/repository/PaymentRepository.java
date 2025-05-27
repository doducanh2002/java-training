package org.aibles.feature.booking.adapter.repository;

import org.aibles.feature.booking.entity.Payment;

public interface PaymentRepository {
    void updatePaymentStatusByTicketId(String ticketId, String status);

  void save(Payment payment);
}
