package org.aibles.feature.booking.business.dto.response;

public class ValidPromotionResponse {

  private String id;
  private String code;
  private String description;
  private String type;
  private Long expiredDate;
  private Integer discount;
  private Long archivedMiles;

  public ValidPromotionResponse() {
  }

  public ValidPromotionResponse(String id, String code, String description, String type,
      Long expiredDate, Integer discount, Long archivedMiles) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.type = type;
    this.expiredDate = expiredDate;
    this.discount = discount;
    this.archivedMiles = archivedMiles;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(Long expiredDate) {
    this.expiredDate = expiredDate;
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

  public Long getArchivedMiles() {
    return archivedMiles;
  }

  public void setArchivedMiles(Long archivedMiles) {
    this.archivedMiles = archivedMiles;
  }
}
