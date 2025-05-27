package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.business.dto.paypal.PaypalOrderDetail;
import org.aibles.business.dto.response.PaypalCaptureResponse;
import org.aibles.business.service.PayPalUseCase;
import org.aibles.feature.booking.adapter.repository.PassengerInfoRepository;
import org.aibles.feature.booking.adapter.repository.PaymentRepository;
import org.aibles.feature.booking.adapter.repository.TicketRepository;
import org.aibles.feature.booking.adapter.repository.TripRepository;
import org.aibles.feature.booking.business.exception.DataNotFoundException;
import org.aibles.feature.booking.business.exception.PaypalCaptureFailureException;
import org.aibles.feature.booking.business.exception.PaypalGetOrderFailureException;
import org.aibles.feature.booking.business.usecase.CapturePurchaseUseCase;
import org.aibles.feature.booking.constant.PaymentStatus;
import org.aibles.feature.booking.constant.PaypalStatus;
import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.entity.Ticket;
import org.aibles.feature.booking.entity.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CapturePurchaseUseCaseImpl implements CapturePurchaseUseCase {
    private final Logger log = LoggerFactory.getLogger(CapturePurchaseUseCaseImpl.class);

    private final PayPalUseCase payPalUseCase;
    private final EmailHelper emailHelper;
    private final PaymentRepository paymentRepository;;
    private final TripRepository tripRepository;
    private final TicketRepository ticketRepository;
    private final PassengerInfoRepository passengerInfoRepository;
    private final CustomerRepository customerRepository;

    public CapturePurchaseUseCaseImpl(PayPalUseCase payPalUseCase, EmailHelper emailHelper, PaymentRepository paymentRepository, TripRepository tripRepository, TicketRepository ticketRepository, PassengerInfoRepository passengerInfoRepository, CustomerRepository customerRepository) {
        this.payPalUseCase = payPalUseCase;
        this.emailHelper = emailHelper;
        this.paymentRepository = paymentRepository;
        this.tripRepository = tripRepository;
        this.ticketRepository = ticketRepository;
        this.passengerInfoRepository = passengerInfoRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public PaypalCaptureResponse invoke(String token) {
        PaypalOrderDetail orderDetail;
        try {
            orderDetail = payPalUseCase.getOrderDetails(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PaypalGetOrderFailureException();
        }
        String ticketId = orderDetail.getPurchaseUnits().get(0).getCustomId();
        Optional<Ticket> ticket= ticketRepository.getTicketByTicketId(ticketId);
        if (ticket.isEmpty()) {
            throw new DataNotFoundException();
        }
        PaypalCaptureResponse paypalResponse;
        try {
            paypalResponse = payPalUseCase.captureOrder(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            paymentRepository.updatePaymentStatusByTicketId(ticketId, PaypalStatus.CAPTURE_FAILED.name());
            throw new PaypalCaptureFailureException();
        }
        if (paypalResponse == null || !PaypalStatus.COMPLETED.name().equals(paypalResponse.getStatus())) {
            paymentRepository.updatePaymentStatusByTicketId(ticketId, PaypalStatus.CAPTURE_FAILED.name());
            throw new PaypalCaptureFailureException();
        }
        paymentRepository.updatePaymentStatusByTicketId(ticketId, PaymentStatus.SUCCESS);
        List<Trip> trips = tripRepository.findCheckInTripByTicketId(ticketId);
        List<PassengerInfo> passengerInfos = passengerInfoRepository.getPassengerInfoByTicketId(ticketId);
        String cusEmail = customerRepository.getCustomerEmailByTicketId(ticketId);
        sendCustomerTicket(ticket.get().getConfirmationCode(), passengerInfos, trips, cusEmail);
        return paypalResponse;
    }

    private void sendCustomerTicket(String ticketCode, List<PassengerInfo> passengerInfos, List<Trip> listTrip, String email) {
        String subject = "AMS Ticket Information";
        StringBuilder passengerName = new StringBuilder();
        for( PassengerInfo passengerInfo : passengerInfos ) {
            passengerName.append(passengerInfo.getFirstName())
                    .append(", ")
                    .append(passengerInfo.getMiddleName()).append(" ")
                    .append(passengerInfo.getLastName()).append("<br/>");
        }
        var param = new HashMap<String, Object>();
        param.put("passengerNames", passengerName.toString());
        param.put("outboundTrip", listTrip.get(0));
        if (listTrip.size() > 1) {
            param.put("returnTrip", listTrip.get(1));
        }
        param.put("ticketCode", ticketCode);
        try {
            emailHelper.send(subject, email, "customer-ticket-template.html", param);
        } catch (Exception e) {
            log.error("(sendAccountToEmail)email: {}", email);
        }
    }
}
