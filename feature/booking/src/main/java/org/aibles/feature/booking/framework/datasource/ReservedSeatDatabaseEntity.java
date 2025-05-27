package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserved_seat")
public class ReservedSeatDatabaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String reservedTripId;
  private String seatId;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public ReservedSeatDatabaseEntity() {
  }

  public ReservedSeatDatabaseEntity(String id, String reservedTripId, String seatId,
      String createdBy,
      Long createdAt, String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.reservedTripId = reservedTripId;
    this.seatId = seatId;
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

  public String getReservedTripId() {
    return reservedTripId;
  }

  public void setReservedTripId(String reservedTripId) {
    this.reservedTripId = reservedTripId;
  }

  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
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
