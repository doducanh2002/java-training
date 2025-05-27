package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.request.RoundTripFilterUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;

public interface RoundTripFilterUseCase {

  List<FilterTripsResponse> invoke(RoundTripFilterUseCaseRequest request);
}
