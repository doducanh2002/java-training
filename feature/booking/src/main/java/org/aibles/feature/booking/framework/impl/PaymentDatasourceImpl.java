package org.aibles.feature.booking.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PaymentRepository;
import org.aibles.feature.booking.entity.Payment;
import org.aibles.feature.booking.framework.repository.PaymentDatabaseEntityRepository;
import org.aibles.feature.booking.util.PaymentMapping;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentDatasourceImpl implements PaymentRepository {

  private final PaymentDatabaseEntityRepository paymentDatabaseEntityRepository;

    @Override
    public void updatePaymentStatusByTicketId(String ticketId, String status) {
        log.info("Updating payment status by ticketId: {}, status {}", ticketId, status);
        paymentDatabaseEntityRepository.updateByTicketId(ticketId, status);
    }
  @Override
  @Transactional
  public void save(Payment payment) {
      paymentDatabaseEntityRepository.save(PaymentMapping.convertEntityToDatabase(payment));
  }
}
