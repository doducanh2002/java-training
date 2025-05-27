package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plane_travel_class")
public class PlaneTravelClassDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String planeId;
  private String travelClassId;
  private Integer capacity;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public PlaneTravelClassDatabaseEntity() {
  }

  public PlaneTravelClassDatabaseEntity(String id, String planeId, String travelClassId,
      Integer capacity, String createdBy, Long createdAt, String lastUpdatedBy,
      Long lastUpdatedAt) {
    this.id = id;
    this.planeId = planeId;
    this.travelClassId = travelClassId;
    this.capacity = capacity;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.lastUpdatedBy = lastUpdatedBy;
    this.lastUpdatedAt = lastUpdatedAt;
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

  public String getTravelClassId() {
    return travelClassId;
  }

  public void setTravelClassId(String travelClassId) {
    this.travelClassId = travelClassId;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public Long getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(Long lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }
}
