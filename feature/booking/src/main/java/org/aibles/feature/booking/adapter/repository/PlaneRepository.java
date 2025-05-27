package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.aibles.feature.booking.entity.Plane;

public interface PlaneRepository {

  List<Plane> findPlanesWithBenefits();
  Plane create(Plane plane);
  Optional<Plane> findByModel(String model);
  Optional<Plane> findById(String id);
  Plane update(Plane plane);
  Plane findByPlaneId(String planeId);
}
