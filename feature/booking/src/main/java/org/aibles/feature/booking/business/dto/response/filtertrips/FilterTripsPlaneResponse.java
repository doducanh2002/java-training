package org.aibles.feature.booking.business.dto.response.filtertrips;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.BenefitResponse;

public class FilterTripsPlaneResponse {
  private String id;
  private String model;
  private List<BenefitResponse> benfits;

  public FilterTripsPlaneResponse() {
  }

  public FilterTripsPlaneResponse(String id, String model) {
    this.id = id;
    this.model = model;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public List<BenefitResponse> getBenfits() {
    return benfits;
  }

  public void setBenfits(
      List<BenefitResponse> benfits) {
    this.benfits = benfits;
  }
}
