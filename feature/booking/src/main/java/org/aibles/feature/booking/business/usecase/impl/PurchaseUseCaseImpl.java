package org.aibles.feature.booking.business.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.business.dto.paypal.PaypalOrderSimple;
import org.aibles.business.dto.request.OrderRequest;
import org.aibles.business.service.PayPalUseCase;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.adapter.repository.*;
import org.aibles.feature.booking.business.dto.request.FlightPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.PassengerInfoPurchaseUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.PurchaseUseCaseRequest;
import org.aibles.feature.booking.business.exception.*;
import org.aibles.feature.booking.business.usecase.PurchaseUseCase;
import org.aibles.feature.booking.constant.PaymentConstant;
import org.aibles.feature.booking.entity.*;
import org.aibles.util.CurrencyUtil;
import org.aibles.util.SecurityUtil;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

import static org.aibles.feature.booking.constant.CacheConstant.RESERVED_SEAT_KEY;
import static org.aibles.feature.booking.constant.PaymentCurrency.VND;

@Slf4j
public class PurchaseUseCaseImpl implements PurchaseUseCase {

  private static final SecureRandom RANDOM = new SecureRandom();

  private final TicketRepository ticketRepository;
  private final BookingCacheRepository bookingCacheRepository;
  private final CustomerRepository customerRepository;
  private final PaymentRepository paymentRepository;
  private final TripContactRepository tripContactRepository;
  private final PassengerInfoRepository passengerInfoRepository;
  private final PassengerAssistanceRepository passengerAssistanceRepository;
  private final ReservedSeatRepository reservedSeatRepository;
  private final ReservedMealRepository reservedMealRepository;
  private final ReservedTripRepository reservedTripRepository;
  private final PayPalUseCase payPalUseCase;
  private final MealRepository mealRepository;
  private final CurrencyUtil currencyUtil;

  public PurchaseUseCaseImpl(TicketRepository ticketRepository,
      BookingCacheRepository bookingCacheRepository, CustomerRepository customerRepository,
      PaymentRepository paymentRepository, TripContactRepository tripContactRepository,
      PassengerInfoRepository passengerInfoRepository,
      PassengerAssistanceRepository passengerAssistanceRepository,
      ReservedSeatRepository reservedSeatRepository, ReservedMealRepository reservedMealRepository,
      ReservedTripRepository reservedTripRepository, PayPalUseCase payPalUseCase,
      MealRepository mealRepository, CurrencyUtil currencyUtil) {
    this.ticketRepository = ticketRepository;
    this.bookingCacheRepository = bookingCacheRepository;
    this.customerRepository = customerRepository;
    this.paymentRepository = paymentRepository;
    this.tripContactRepository = tripContactRepository;
    this.passengerInfoRepository = passengerInfoRepository;
    this.passengerAssistanceRepository = passengerAssistanceRepository;
    this.reservedSeatRepository = reservedSeatRepository;
    this.reservedMealRepository = reservedMealRepository;
    this.reservedTripRepository = reservedTripRepository;
    this.payPalUseCase = payPalUseCase;
    this.mealRepository = mealRepository;
    this.currencyUtil = currencyUtil;
  }

  @Override
  public PaypalOrderSimple invoke(PurchaseUseCaseRequest request) {
    Optional<Object> totalPrice = bookingCacheRepository.get(request.getTicketId());
    if (totalPrice.isEmpty()) {
      throw new TicketNotFoundException(request.getTicketId());
    }
    if (totalPrice.get() instanceof Number) {
      long storedPrice = ((Number) totalPrice.get()).longValue();
      if (storedPrice != request.getTotalPrice()) {
        throw new TotalPriceNotMatchException();
      }
    } else {
      throw new TotalPriceWrongFormatException();
    }
    Customer customer = new Customer();
    if (SecurityUtil.getCredential().isEmpty()) {
      customer.setEmail(request.getTripContact().getPrimaryEmail());
      customer = customerRepository.save(customer);
    } else {
      customer = customerRepository.findByAccountId(SecurityUtil.getCredential());
    }

    int totalSeats =request.getTrips().stream()
        .flatMap(tripRequest -> tripRequest.getFlights().stream())
        .mapToInt(flightRequest -> flightRequest.getSeats().size())
        .sum();

    Ticket ticket = new Ticket();
    ticket.setId(request.getTicketId());
    ticket.setNumOfPassenger( String.valueOf(totalSeats));
    ticket.setConfirmationCode(generateRandomString());
    ticket.setNumOfPassenger(String.valueOf(request.getPassengerInfos().size()));
    ticket.setCustomerId(customer.getId());
    ticket.setTripType(request.getTripType());
    Promotion promotion = new Promotion();
    if (request.getPromotion() != null) {
      promotion.setId(request.getPromotion());
    }
    ticket.setPromotion(promotion);
    ticketRepository.save(ticket);

    Payment payment = new Payment();
    payment.setAmount(request.getTotalPrice());
    payment.setPaymentStatus(PaymentConstant.IN_PROGRESS.name());
    payment.setTicket(ticket);
    paymentRepository.save(payment);

    TripContact tripContact = new TripContact();
    tripContact.setPrimaryEmail(request.getTripContact().getPrimaryEmail());
    tripContact.setSecondaryEmail(request.getTripContact().getSecondaryEmail());
    tripContact.setPhoneNumber(request.getTripContact().getPhoneNumber());
    tripContact.setTicket(ticket);
    tripContactRepository.save(tripContact);

    List<PassengerInfo> passengerInfos = new ArrayList<>();
    for (PassengerInfoPurchaseUseCaseRequest purchaseUseCase : request.getPassengerInfos()) {
      List<SpecialAssistance> specialAssistances = request.getSpecialAssistances().stream()
          .map(s -> {
            SpecialAssistance specialAssistance = new SpecialAssistance();
            specialAssistance.setId(s);
            return specialAssistance;
          }).collect(Collectors.toList());
      passengerInfos.add(PassengerInfo.from(purchaseUseCase, ticket, specialAssistances));
    }
    List<PassengerInfo> passengerInfoList = passengerInfoRepository.saveAll(passengerInfos);

    List<PassengerAssistance> passengerAssistances = new ArrayList<>();
    for (String specialAssistance : request.getSpecialAssistances()) {
      for (PassengerInfo passengerInfo : passengerInfoList) {
        PassengerAssistance passengerAssistance = new PassengerAssistance();
        passengerAssistance.setPassengerInfo(passengerInfo);
        passengerAssistance.setSpecialAssistance(new SpecialAssistance(specialAssistance));
        passengerAssistances.add(passengerAssistance);
      }
    }
    passengerAssistanceRepository.saveAll(passengerAssistances);

    List<ReservedTrip> reservedTrips = request.getTrips().stream().map(tripRequest -> {
      Trip trip = new Trip();
      trip.setId(tripRequest.getId());

      FlightPurchaseUseCaseRequest flight = tripRequest.getFlights().get(0);
      Fare fare = new Fare();
      fare.setId(flight.getFareId());

      ReservedTrip reservedTrip = new ReservedTrip();
      reservedTrip.setTrip(trip);
      reservedTrip.setFare(fare);
      reservedTrip.setTicket(ticket);

      return reservedTrip;
    }).toList();

    List<ReservedTrip> savedReservedTrips = reservedTripRepository.saveAll(reservedTrips);

    Set<ReservedSeat> reservedSeats = new HashSet<>();
    Set<ReservedMeal> reservedMeals = new HashSet<>();

    List<Meal> meals = mealRepository.getMealByIdIn(request.getTrips().stream()
        .flatMap(trip -> trip.getFlights().stream())
        .map(FlightPurchaseUseCaseRequest::getMealId)
        .toList());
    Map<String, Meal> mealMap = meals.isEmpty() ? new HashMap<>()
            : meals.stream().collect(Collectors.toMap(meal -> String.valueOf(meal.getId()), meal -> meal));

    Map<String, ReservedTrip> reservedTripMap = savedReservedTrips.stream()
        .collect(Collectors.toMap(rt -> rt.getTrip().getId(), rt -> rt));

    request.getTrips().forEach(tripRequest -> {
      Set<Integer> seatCounts = new HashSet<>();
      ReservedTrip reservedTrip = reservedTripMap.get(tripRequest.getId());
      if (reservedTrip != null) {
        tripRequest.getFlights().forEach(flightRequest -> {

          String redisKey = RESERVED_SEAT_KEY + "_" + flightRequest.getId();
          bookingCacheRepository.add(redisKey, new HashSet<>(flightRequest.getSeats()), String.class);

          flightRequest.getSeats().forEach(seatId -> {
            ReservedSeat reservedSeat = new ReservedSeat();
            reservedSeat.setReservedTrip(reservedTrip);
            Seat seat = new Seat();
            seat.setId(seatId);
            reservedSeat.setSeat(seat);
            reservedSeats.add(reservedSeat);

            if (mealMap.get(flightRequest.getMealId()) != null) {
                Meal meal = new Meal();
                if (flightRequest.getMealId() != null) {
                  meal.setId(flightRequest.getMealId());
                }
                ReservedMeal reservedMeal = new ReservedMeal();
                reservedMeal.setReservedTrip(reservedTrip);
                reservedMeal.setPrice(mealMap.get(flightRequest.getMealId()).getPrice());
                reservedMeal.setMeal(meal);
                reservedMeals.add(reservedMeal);
            }
          });
          seatCounts.add(flightRequest.getSeats().size());
        });
      }
      if (seatCounts.size() > 1) {
        throw new NumberSeatDoesNotMatchException();
      }
    }
    );

    reservedSeatRepository.saveAll(new ArrayList<>(reservedSeats));
    reservedMealRepository.saveAll(new ArrayList<>(reservedMeals));

    List<OrderRequest> orderRequests = new ArrayList<>();
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setOrderId(ticket.getId());
    orderRequest.setAmount(request.getTotalPrice());
    orderRequests.add(orderRequest);

    for(OrderRequest order : orderRequests){
      order.setAmount(currencyUtil.convertToUSD(order.getAmount(), VND.name()));
    }

    bookingCacheRepository.delete(request.getTicketId());

    try {
      return payPalUseCase.createOrder(orderRequests);
    } catch (Exception e) {
      throw new PurchaseBadRequestException();
    }
  }

  public String generateRandomString() {
    StringBuilder sb = new StringBuilder(6);
    for (int i = 0; i < 6; i++) {
      if (RANDOM.nextBoolean()) {
        sb.append((char) (RANDOM.nextInt(26) + 'A'));
      } else {
        sb.append(RANDOM.nextInt(10));
      }
    }
    return sb.toString();
  }
}
