package org.aibles.feature.booking.framework.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.aibles.business.dto.paypal.PaypalOrderSimple;
import org.aibles.feature.booking.business.dto.request.PassengerInfoPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.PurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.TripContactPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.TripPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.CalculatePriceResponse;
import org.aibles.feature.booking.business.usecase.CalculatePriceUseCase;
import org.aibles.feature.booking.business.usecase.PurchaseUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.PurchaseRequest;
import org.aibles.feature.booking.framework.controller.dto.request.calculateprice.CalculatePriceRequest;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.aibles.feature.booking.util.PurchaseMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final PurchaseUseCase purchaseUseCase;

  private final CalculatePriceUseCase calculatePriceUseCase;

  @PostMapping("/payment")
  public PaypalOrderSimple purchaseOrder(@RequestBody @Valid PurchaseRequest request) {

    List<TripPurchaseUseCaseRequest> tripPurchaseUseCaseRequests = request.getTrips().stream()
        .map(PurchaseMapping::convertToTripPurchaseUseCaseRequest)
        .collect(Collectors.toList());

    List<PassengerInfoPurchaseUseCaseRequest> passengerInfoPurchaseUseCaseRequests = request.getPassengerInfo().stream()
        .map(PurchaseMapping::convertToPassengerInfoPurchaseUseCaseRequest)
        .collect(Collectors.toList());

    TripContactPurchaseUseCaseRequest tripContact = TripContactPurchaseUseCaseRequest.builder()
        .primaryEmail(request.getTripContact().getPrimaryEmail())
        .secondaryEmail(request.getTripContact().getSecondaryEmail())
        .phoneNumber(request.getTripContact().getPhoneNumber())
        .build();

    PurchaseUseCaseRequest purchaseUseCaseRequest = PurchaseUseCaseRequest.builder()
        .ticketId(request.getTicketId())
        .totalPrice(request.getTotalPrice())
        .tripType(request.getTripType())
        .trips(tripPurchaseUseCaseRequests)
        .passengerInfos(passengerInfoPurchaseUseCaseRequests)
        .tripContact(tripContact)
        .specialAssistances(request.getSpecialAssistances())
        .promotion(request.getPromotion())
        .build();

    return purchaseUseCase.invoke(purchaseUseCaseRequest);
  }

  @PostMapping("/calculation")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<CalculatePriceResponse> calculatePrice(@RequestBody CalculatePriceRequest request) {
    CalculatePriceResponse response = calculatePriceUseCase.invoke(
        CalculatePriceRequest.to(request)
    );
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), response);
  }
}
