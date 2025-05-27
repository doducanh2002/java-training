package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import org.aibles.feature.booking.entity.SpecialAssistance;

public interface SpecialAssistanceRepository {
  List<SpecialAssistance> findAll();
}
