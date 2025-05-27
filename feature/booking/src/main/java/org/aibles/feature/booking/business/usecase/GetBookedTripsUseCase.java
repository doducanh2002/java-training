package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.TripResponse;

public interface GetBookedTripsUseCase {

  List<TripResponse> invoke(String accountId);
}
