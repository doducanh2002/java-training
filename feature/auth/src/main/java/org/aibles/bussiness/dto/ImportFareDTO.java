package org.aibles.bussiness.dto;

public class ImportFareDTO {

  private String id;
  private String nameTravelClass;
  private String fare;
  private String benefit;
  private Long price;

  public ImportFareDTO() {
  }

  public ImportFareDTO(String id, String nameTravelClass, String fare, String benefit, Long price) {
    this.id = id;
    this.nameTravelClass = nameTravelClass;
    this.fare = fare;
    this.benefit = benefit;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNameTravelClass() {
    return nameTravelClass;
  }

  public void setNameTravelClass(String nameTravelClass) {
    this.nameTravelClass = nameTravelClass;
  }

  public String getFare() {
    return fare;
  }

  public void setFare(String fare) {
    this.fare = fare;
  }

  public String getBenefit() {
    return benefit;
  }

  public void setBenefit(String benefit) {
    this.benefit = benefit;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
