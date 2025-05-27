package org.aibles.feature.booking.business.dto.response;

import java.time.LocalDateTime;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;

public class PromotionResponse {

  private String id;
  private String code;
  private String description;
  private LocalDateTime expiredDate;
  private LocalDateTime giveAwayDay;
  private Integer discountPercent;
  private Long archivedMiles;
  private Long plusMiles;
  private Long quantity;
  private boolean isQualified;
  private boolean isWinner;
  private boolean isUsed;

  public PromotionResponse() {
  }

  public static PromotionResponse from(Promotion promotion, CustomerPromotion customerPromotion) {
    PromotionResponse response  = new PromotionResponse();
    response.setId(promotion.getId());
    response.setCode(promotion.getCode());
    response.setDescription(promotion.getDescription());
    response.setExpiredDate(promotion.getExpiredDate());
    response.setGiveAwayDay(promotion.getGiveAwayDay());
    response.setDiscountPercent(promotion.getDiscountPercent());
    response.setArchivedMiles(promotion.getArchivedMiles());
    response.setPlusMiles(promotion.getPlusMiles());
    response.setQuantity(promotion.getQuantity());
    return response;
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


  public boolean getIsQualified() {
    return isQualified;
  }

  public void setIsQualified(boolean isQualified) {
    this.isQualified = isQualified;
  }

  public boolean getIsWinner() {
    return isWinner;
  }

  public void setIsWinner(boolean isWinner) {
    this.isWinner = isWinner;
  }

  public boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(boolean isUsed) {
    this.isUsed = isUsed;
  }

}
