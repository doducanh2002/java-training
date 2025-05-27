package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.framework.datasource.BenefitDatabaseEntity;

public class BenefitMapping {

  public static Benefits convertDatabaseToEntity(BenefitDatabaseEntity databaseEntity) {
    Benefits benefits = new Benefits();
    benefits.setId(databaseEntity.getId());
    benefits.setName(databaseEntity.getName());
    return benefits;
  }

  public static List<Benefits> convertDatabaseToEntity(
      List<BenefitDatabaseEntity> benefitDatabaseEntities) {
    List<Benefits> benefitsList = new ArrayList<>();
    for (BenefitDatabaseEntity databaseEntity : benefitDatabaseEntities) {
      Benefits benefits = BenefitMapping.convertDatabaseToEntity(databaseEntity);
      benefitsList.add(benefits);
    }
    return benefitsList;
  }

  public static Benefits convertIdToBenefit(String benefitId) {
    Benefits benefits = new Benefits();
    benefits.setId(benefitId);
    return benefits;
  }

  public static BenefitDatabaseEntity convertEntityToDatabase(Benefits benefits) {
    BenefitDatabaseEntity databaseEntity = new BenefitDatabaseEntity();
    databaseEntity.setId(benefits.getId());
    databaseEntity.setName(benefits.getName());
    return databaseEntity;
  }

  public static List<BenefitDatabaseEntity> convertEntityToDatabase(
      List<Benefits> benefits) {
    List<BenefitDatabaseEntity> databaseEntities = new ArrayList<>();
    for (Benefits benefit : benefits) {
      BenefitDatabaseEntity benefitsEntity = BenefitMapping.convertEntityToDatabase(benefit);
      databaseEntities.add(benefitsEntity);
    }
    return databaseEntities;
  }
}
