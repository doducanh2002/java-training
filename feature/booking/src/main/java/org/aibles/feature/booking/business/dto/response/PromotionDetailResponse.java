package org.aibles.feature.booking.business.dto.response;

import java.time.LocalDateTime;

public class PromotionDetailResponse {

  private String id;
  private String code;
  private String description;
  private LocalDateTime expiredDate;
  private LocalDateTime giveAwayDay;
  private Integer discountPercent;
  private Long archivedMiles;
  private Long plusMiles;
  private Long quantity;
  private Boolean isQualified;
  private Boolean isWinner;
  private Boolean isUsed;

  public PromotionDetailResponse() {
  }

  public PromotionDetailResponse(String id, String code, String description,
      LocalDateTime expiredDate, LocalDateTime giveAwayDay, Integer discountPercent,
      Long archivedMiles, Long plusMiles, Long quantity, Boolean isQualified, Boolean isWinner,
      Boolean isUsed) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.expiredDate = expiredDate;
    this.giveAwayDay = giveAwayDay;
    this.discountPercent = discountPercent;
    this.archivedMiles = archivedMiles;
    this.plusMiles = plusMiles;
    this.quantity = quantity;
    this.isQualified = isQualified;
    this.isWinner = isWinner;
    this.isUsed = isUsed;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(LocalDateTime expiredDate) {
    this.expiredDate = expiredDate;
  }

  public LocalDateTime getGiveAwayDay() {
    return giveAwayDay;
  }

  public void setGiveAwayDay(LocalDateTime giveAwayDay) {
    this.giveAwayDay = giveAwayDay;
  }

  public Integer getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(Integer discountPercent) {
    this.discountPercent = discountPercent;
  }

  public Long getArchivedMiles() {
    return archivedMiles;
  }

  public void setArchivedMiles(Long archivedMiles) {
    this.archivedMiles = archivedMiles;
  }

  public Long getPlusMiles() {
    return plusMiles;
  }

  public void setPlusMiles(Long plusMiles) {
    this.plusMiles = plusMiles;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Boolean getQualified() {
    return isQualified;
  }

  public void setQualified(Boolean qualified) {
    isQualified = qualified;
  }

  public Boolean getWinner() {
    return isWinner;
  }

  public void setWinner(Boolean winner) {
    isWinner = winner;
  }

  public Boolean getUsed() {
    return isUsed;
  }

  public void setUsed(Boolean used) {
    isUsed = used;
  }
}
