package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.ReservedTrip;

public interface ReservedTripRepository {

  List<ReservedTrip> saveAll(List<ReservedTrip> reservedTrips);
}
