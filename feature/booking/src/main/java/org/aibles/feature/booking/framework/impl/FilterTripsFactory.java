package org.aibles.feature.booking.framework.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;
import org.aibles.feature.booking.business.usecase.OneWayFilterUseCase;
import org.aibles.feature.booking.business.usecase.RoundTripFilterUseCase;
import org.aibles.feature.booking.framework.controller.constant.TripType;
import org.aibles.feature.booking.framework.controller.dto.request.FilterTripsRequest;
import org.aibles.feature.booking.framework.controller.dto.request.OneWayFilterRequest;
import org.aibles.feature.booking.framework.controller.dto.request.RoundTripFilterRequest;
import org.aibles.feature.booking.framework.validation.ValidatorHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilterTripsFactory {

  private final ObjectMapper objectMapper;

  private final ValidatorHelper validatorHelper;

  private final ApplicationContext applicationContext;

  public FilterTripsFactory(ObjectMapper objectMapper, ValidatorHelper validatorHelper,
      ApplicationContext applicationContext) {
    this.objectMapper = objectMapper;
    this.validatorHelper = validatorHelper;
    this.applicationContext = applicationContext;
  }

  public List<FilterTripsResponse> invoke(FilterTripsRequest filterTripsRequest) {
    List<FilterTripsResponse> response = new ArrayList<>();
    TripType tripType = TripType.valueOf(filterTripsRequest.getType());
    Object filterTripsBean = applicationContext.getBean(tripType.getBeanName());
    switch (tripType) {
      case ONE_WAY -> {
        OneWayFilterRequest request =
            objectMapper.convertValue(filterTripsRequest.getFilter(),
                OneWayFilterRequest.class);
        validatorHelper.validate(request);
        response = ((OneWayFilterUseCase) filterTripsBean).invoke(OneWayFilterRequest.toUseCaseRequest(request));
      }
      case ROUND_TRIP -> {
        RoundTripFilterRequest request = objectMapper.convertValue(filterTripsRequest.getFilter(),
            RoundTripFilterRequest.class);
        validatorHelper.validate(request);
        response = ((RoundTripFilterUseCase) filterTripsBean).invoke(RoundTripFilterRequest.toUseCaseRequest(request));
      }
    }
    return response;
  }
}
