package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.ValidateSeatResponse;

public interface ValidateSeatUseCase {
  ValidateSeatResponse invoke(String tripId, String fareId);
}