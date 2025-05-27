package org.aibles.feature.booking.business.dto.response.filtertrips;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.BenefitResponse;

public class FilterTripsFareResponse {

  private String id;
  private String name;
  private Long price;
  private List<BenefitResponse> benefits;

  public FilterTripsFareResponse() {
  }

  public FilterTripsFareResponse(String id, String name, Long price, List<BenefitResponse> benefits) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.benefits = benefits;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public List<BenefitResponse> getBenefits() {
    return benefits;
  }

  public void setBenefits(
      List<BenefitResponse> benefits) {
    this.benefits = benefits;
  }

}
