package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fare_benefit")
public class FareBenefitDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String fareId;
  private String benefitId;

  public FareBenefitDatabaseEntity() {
  }

  public FareBenefitDatabaseEntity(String id, String fareId, String benefitId) {
    this.id = id;
    this.fareId = fareId;
    this.benefitId = benefitId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFareId() {
    return fareId;
  }

  public void setFareId(String fareId) {
    this.fareId = fareId;
  }

  public String getBenefitId() {
    return benefitId;
  }

  public void setBenefitId(String benefitId) {
    this.benefitId = benefitId;
  }
}
