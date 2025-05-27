package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat")
public class SeatDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private Integer seatRow;
  private String seatColumn;
  private Integer seatPitch;
  private Integer seatWidth;
  private String planeTravelClassId;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public SeatDatabaseEntity() {
  }

  public SeatDatabaseEntity(String id, Integer seatRow, String seatColumn, Integer seatPitch,
      Integer seatWidth, String planeTravelClassId, String createdBy, Long createdAt,
      String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.seatRow = seatRow;
    this.seatColumn = seatColumn;
    this.seatPitch = seatPitch;
    this.seatWidth = seatWidth;
    this.planeTravelClassId = planeTravelClassId;
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

  public Integer getSeatPitch() {
    return seatPitch;
  }

  public void setSeatPitch(Integer seatPitch) {
    this.seatPitch = seatPitch;
  }

  public Integer getSeatWidth() {
    return seatWidth;
  }

  public void setSeatWidth(Integer seatWidth) {
    this.seatWidth = seatWidth;
  }

  public String getPlaneTravelClassId() {
    return planeTravelClassId;
  }

  public void setPlaneTravelClassId(String planeTravelClassId) {
    this.planeTravelClassId = planeTravelClassId;
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

  public Integer getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(Integer seatRow) {
    this.seatRow = seatRow;
  }

  public String getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(String seatColumn) {
    this.seatColumn = seatColumn;
  }
}
