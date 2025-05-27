package org.aibles.feature.booking.framework.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class TicketDatabaseEntity {

  @Id
  private String id;
  private String confirmationCode;
  private String numOfPassenger;
  private String customerId;
  private String promoId;
  private String tripType;
  private String createdBy;
  private Long createdAt;
  private String lastUpdatedBy;
  private Long lastUpdatedAt;

  public TicketDatabaseEntity() {
  }

  public TicketDatabaseEntity(String id, String confirmationCode, String numOfPassenger,
      String customerId, String promoId, String tripType, String createdBy, Long createdAt,
      String lastUpdatedBy, Long lastUpdatedAt) {
    this.id = id;
    this.confirmationCode = confirmationCode;
    this.numOfPassenger = numOfPassenger;
    this.customerId = customerId;
    this.promoId = promoId;
    this.tripType = tripType;
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

  public String getConfirmationCode() {
    return confirmationCode;
  }

  public void setConfirmationCode(String confirmationCode) {
    this.confirmationCode = confirmationCode;
  }

  public String getNumOfPassenger() {
    return numOfPassenger;
  }

  public void setNumOfPassenger(String numOfPassengers) {
    this.numOfPassenger = numOfPassengers;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getPromoId() {
    return promoId;
  }

  public void setPromoId(String promoId) {
    this.promoId = promoId;
  }

  public String getTripType() {
    return tripType;
  }

  public void setTripType(String tripType) {
    this.tripType = tripType;
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
