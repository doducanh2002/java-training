package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.business.dto.response.planedetail.TravelClassDetailResponse;
import org.aibles.feature.booking.entity.TravelClass;

public interface TravelClassRepository {

  List<TravelClass> getTravelClassList();

  List<TravelClass> findDetailByPlaneId(String planeId);

  Optional<TravelClass> findByName(String name);

  List<TravelClassDetailResponse> findByPlaneId(String planeId);
}
