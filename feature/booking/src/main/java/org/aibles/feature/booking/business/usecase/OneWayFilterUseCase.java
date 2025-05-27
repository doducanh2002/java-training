package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.request.OneWayFilterUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.filtertrips.FilterTripsResponse;

public interface OneWayFilterUseCase {

  List<FilterTripsResponse> invoke(OneWayFilterUseCaseRequest request);
}
