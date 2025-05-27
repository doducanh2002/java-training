package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.SpecialAssistance;
import org.aibles.feature.booking.framework.datasource.SpecialAssistanceDatabaseEntity;

public class SpecialAssistanceMapping {
  public static SpecialAssistance convertToEntity(SpecialAssistanceDatabaseEntity databaseEntity) {
    SpecialAssistance specialAssistance = new SpecialAssistance();
    specialAssistance.setId(databaseEntity.getId());
    specialAssistance.setName(databaseEntity.getName());
    specialAssistance.setDescription(databaseEntity.getDescription());
    return specialAssistance;
  }

  public static List<SpecialAssistance> convertToEntity(
      List<SpecialAssistanceDatabaseEntity> databaseEntities) {
    List<SpecialAssistance> specialAssistances = new ArrayList<>();
    for (SpecialAssistanceDatabaseEntity databaseEntity : databaseEntities) {
      specialAssistances.add(convertToEntity(databaseEntity));
    }
    return specialAssistances;
  }
}
