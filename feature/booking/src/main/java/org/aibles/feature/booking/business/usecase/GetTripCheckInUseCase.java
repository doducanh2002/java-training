package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.TripResponse;

public interface GetTripCheckInUseCase {

  List<TripResponse> invoke(String passengerLastName, String confirmationCode);
}
