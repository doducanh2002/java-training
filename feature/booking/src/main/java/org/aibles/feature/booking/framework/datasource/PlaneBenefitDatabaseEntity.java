package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plane_benefit")
public class PlaneBenefitDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String planeId;
  private String benefitId;

  public PlaneBenefitDatabaseEntity() {
  }

  public PlaneBenefitDatabaseEntity(String id, String planeId, String benefitId) {
    this.id = id;
    this.planeId = planeId;
    this.benefitId = benefitId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPlaneId() {
    return planeId;
  }

  public void setPlaneId(String planeId) {
    this.planeId = planeId;
  }

  public String getBenefitId() {
    return benefitId;
  }

  public void setBenefitId(String benefitId) {
    this.benefitId = benefitId;
  }
}
