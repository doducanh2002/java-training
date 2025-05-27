package org.aibles.feature.booking.adapter.repository;

import java.util.Optional;
import org.aibles.feature.booking.entity.PlaneTravelClass;

public interface PlaneTravelClassRepository {

  PlaneTravelClass save(PlaneTravelClass planeTravelClass);

  Optional<PlaneTravelClass> findByNameAndPlane(String name, String planeId);
}
