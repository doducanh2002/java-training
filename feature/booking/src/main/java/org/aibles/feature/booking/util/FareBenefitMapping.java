package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.FareBenefit;
import org.aibles.feature.booking.framework.datasource.FareBenefitDatabaseEntity;

public class FareBenefitMapping {


  public static FareBenefitDatabaseEntity convertEntityToDatabase(FareBenefit fareBenefit) {
    FareBenefitDatabaseEntity database = new FareBenefitDatabaseEntity();
    database.setId(fareBenefit.getId());
    database.setFareId(fareBenefit.getFare().getId());
    database.setBenefitId(fareBenefit.getBenefit().getId());
    return database;
  }

  public static List<FareBenefitDatabaseEntity> convertEntityToDatabase(
      List<FareBenefit> fareBenefits) {
    List<FareBenefitDatabaseEntity> databaseEntities = new ArrayList<>();
    for (FareBenefit fareBenefit : fareBenefits) {
      FareBenefitDatabaseEntity fareBenefitEntity = FareBenefitMapping.convertEntityToDatabase(fareBenefit);
      databaseEntities.add(fareBenefitEntity);
    }
    return databaseEntities;
  }
}
