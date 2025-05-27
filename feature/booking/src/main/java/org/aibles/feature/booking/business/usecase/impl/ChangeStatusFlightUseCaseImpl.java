package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.BookingCacheRepository;
import org.aibles.feature.booking.adapter.repository.FlightRepository;
import org.aibles.feature.booking.business.dto.request.ChangeFlightStatusUsecaseRequest;
import org.aibles.feature.booking.business.exception.FlightNotFoundException;
import org.aibles.feature.booking.business.usecase.ChangeStatusFlightUseCase;
import org.aibles.feature.booking.entity.Flight;
import org.aibles.feature.booking.framework.datasource.FlightDatabaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.aibles.feature.booking.constant.CacheConstant.RESERVED_SEAT_KEY;

public class ChangeStatusFlightUseCaseImpl implements ChangeStatusFlightUseCase {

    private final Logger log = LoggerFactory.getLogger(ChangeStatusFlightUseCaseImpl.class);

    private final FlightRepository flightRepository;
    private final BookingCacheRepository bookingCacheRepository;

    public ChangeStatusFlightUseCaseImpl(FlightRepository flightRepository, BookingCacheRepository bookingCacheRepository) {
        this.flightRepository = flightRepository;
        this.bookingCacheRepository = bookingCacheRepository;
    }

    @Override
    @Transactional
    public void invoke(ChangeFlightStatusUsecaseRequest request) {
        log.info("Starting to change status of flight with ID: {} to {}", request.getFlightId(), request.getStatus());

        Optional<FlightDatabaseEntity> updatedStatusFlight = flightRepository.findByFlightId(request.getFlightId());

        FlightDatabaseEntity flight = updatedStatusFlight
                .orElseThrow(() -> new FlightNotFoundException("Flight with ID " + request.getFlightId() + " not found"));

        flight.setStatus(request.getStatus());
        log.info("Successfully updated flight ID {} to status {}", flight.getId(), flight.getStatus());

        if ("DELAYED".equalsIgnoreCase(request.getStatus()) || "DEPARTED".equalsIgnoreCase(request.getStatus())) {
            String cacheKey = RESERVED_SEAT_KEY + request.getFlightId();
            log.info("Deleting Redis cache key: {}", cacheKey);
            bookingCacheRepository.delete(cacheKey, request.getFlightId());
        }
    }

}
