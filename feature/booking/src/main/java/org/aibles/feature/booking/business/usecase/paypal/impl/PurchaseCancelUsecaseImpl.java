package org.aibles.feature.booking.business.usecase.paypal.impl;

import static org.aibles.feature.booking.constant.CacheConstant.RESERVED_SEAT_KEY;
import static org.aibles.feature.booking.constant.PaymentStatus.CANCEL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.business.dto.paypal.PaypalOrderDetail;
import org.aibles.business.service.PayPalUseCase;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.adapter.repository.PaymentRepository;
import org.aibles.feature.booking.adapter.repository.ReservedSeatRepository;
import org.aibles.feature.booking.business.exception.PaypalGetOrderFailureException;
import org.aibles.feature.booking.business.usecase.paypal.PurchaseCancelUsecase;

public class PurchaseCancelUsecaseImpl implements PurchaseCancelUsecase {
  private final PayPalUseCase payPalUseCase;
  private final PaymentRepository paymentRepository;
  private final BookingCacheRepository bookingCacheRepository;
  private final CustomerRepository customerRepository;
  private final EmailHelper emailHelper;
  private final FlightRepository flightRepository;
  private final ReservedSeatRepository reservedSeatRepository;

  public PurchaseCancelUsecaseImpl(
      PayPalUseCase payPalUseCase,
      PaymentRepository paymentRepository,
      BookingCacheRepository bookingCacheRepository,
      CustomerRepository customerRepository,
      EmailHelper emailHelper,
      FlightRepository flightRepository,
      ReservedSeatRepository reservedSeatRepository) {
    this.payPalUseCase = payPalUseCase;
    this.paymentRepository = paymentRepository;
    this.bookingCacheRepository = bookingCacheRepository;
    this.customerRepository = customerRepository;
    this.emailHelper = emailHelper;
    this.flightRepository = flightRepository;
    this.reservedSeatRepository = reservedSeatRepository;
  }

  @Override
  public void invoke(String paypalToken) {
    PaypalOrderDetail orderDetail;
    try {
      orderDetail = payPalUseCase.getOrderDetails(paypalToken);
    } catch (Exception e) {
      throw new PaypalGetOrderFailureException();
    }

    String ticketId = orderDetail.getPurchaseUnits().get(0).getCustomId();

    paymentRepository.updatePaymentStatusByTicketId(ticketId, CANCEL);

    List<String> flightIds = flightRepository.findFlightIdByTicketId(ticketId);
    if (!flightIds.isEmpty()) {
      List<String> reservedSeatByTicketId =
          reservedSeatRepository.getReservedSeatsByTicketId(ticketId);
      Set<String> reservedSeatSet = new HashSet<>(reservedSeatByTicketId);
      for (String flightId : flightIds) {
        String reservedSeatKey = RESERVED_SEAT_KEY + flightId;
        bookingCacheRepository.remove(reservedSeatKey, reservedSeatSet);
      }
    }

    String customerEmail = customerRepository.getCustomerEmailByTicketId(ticketId);
    var param = new HashMap<String, Object>();
    param.put("orderId", paypalToken);
    emailHelper.send("Cancel Purchase", customerEmail, "purchase-cancel-noti", param);
  }
}
