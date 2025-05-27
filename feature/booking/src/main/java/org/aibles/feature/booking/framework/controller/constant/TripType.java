package org.aibles.feature.booking.framework.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TripType {

  ONE_WAY("oneWayFilterUseCase"),
  ROUND_TRIP("roundTripFilterUseCase");

  private final String beanName;
}
