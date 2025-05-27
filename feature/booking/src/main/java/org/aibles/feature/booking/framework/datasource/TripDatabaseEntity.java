package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trip")
public class TripDatabaseEntity {

  @Id
  private String id;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String departure;
  private String arrival;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public TripDatabaseEntity() {
  }

  public TripDatabaseEntity(String id, LocalDateTime startDate, LocalDateTime endDate, String departure,
      String arrival,
      String createdBy, Long createdAt, String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.departure = departure;
    this.arrival = arrival;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.lastUpdatedBy = lastUpdatedBy;
    this.lastUpdatedAt = lastUpdatedAt;
  }

  @PrePersist
  private void generateTripId() {
    this.id = this.id != null ? this.id : UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    this.arrival = arrival;
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
