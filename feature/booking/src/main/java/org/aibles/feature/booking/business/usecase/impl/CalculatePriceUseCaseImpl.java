package org.aibles.feature.booking.business.usecase.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.adapter.repository.MealRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceTripUseCaseRequest;
import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.CalculatePriceResponse;
import org.aibles.feature.booking.business.exception.PromotionNotFoundException;
import org.aibles.feature.booking.business.usecase.CalculatePriceUseCase;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.Meal;
import org.aibles.feature.booking.entity.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatePriceUseCaseImpl implements CalculatePriceUseCase {

  private final MealRepository mealRepository;

  private final FareRepository fareRepository;

  private final PromotionRepository promotionRepository;

  private final BookingCacheRepository redisRepository;

  private final Logger log = LoggerFactory.getLogger(CalculatePriceUseCaseImpl.class);

  public CalculatePriceUseCaseImpl(MealRepository mealRepository, FareRepository fareRepository,
      PromotionRepository promotionRepository,
      BookingCacheRepository redisRepository) {
    this.mealRepository = mealRepository;
    this.fareRepository = fareRepository;
    this.promotionRepository = promotionRepository;
    this.redisRepository = redisRepository;
  }

  @Override
  public CalculatePriceResponse invoke(CalculatePriceUseCaseRequest request) {

    int totalTrip = request.getTrips().size();

    Set<String> fareId = request.getTrips().stream()
        .map(CalculatePriceTripUseCaseRequest::getFareId).collect(
        Collectors.toSet());

    Map<String, String> mealMap = new HashMap<>();
    request.getTrips().forEach(
        trip ->
          trip.getFlights().forEach(flight ->
            mealMap.put(flight.getId(), flight.getMealId())
          )
    );

    log.info("(invoke)calculating fare : {} and meal : {} and promotion : {}",
        fareId, mealMap, request.getPromotionId());

    long totalPrice;

    long farePrices = fareRepository.findByIds(fareId.stream().toList())
        .stream().mapToLong(Fare::getPrice).sum();

    long mealPrices = mealRepository.findByFlightIdAndId(mealMap)
        .stream().mapToLong(Meal::getPrice).sum();

    long totalFare = (fareId.size() == 1 && totalTrip == 2) ? farePrices * 2 : farePrices;

    totalPrice = (totalFare + mealPrices) * request.getNumOfPassengers();

    log.info("(invoke) totalPrice: {}", totalPrice);
    if (request.getPromotionId() != null) {
      Promotion promotion = promotionRepository.findById(request.getPromotionId())
          .orElseThrow(() -> new PromotionNotFoundException(request.getPromotionId()));
      if (promotion.getDiscountPercent() != null) {
        log.info("(invoke)discountPercent: {}", promotion.getDiscountPercent());
        totalPrice = (totalPrice * (100 - promotion.getDiscountPercent())) / 100;
      }
    }

    String ticketId = UUID.randomUUID().toString();

    redisRepository.save(ticketId, totalPrice, 1, TimeUnit.DAYS);

    CalculatePriceResponse response = new CalculatePriceResponse();
    response.setTicketId(ticketId);
    response.setPrice(totalPrice);
    return response;
  }
}
