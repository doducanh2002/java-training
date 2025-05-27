package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.BookingStatusResponse;

public interface GetPlaneFareStatusUsecase {

  BookingStatusResponse invoke(String flightId, String fareId);
}
