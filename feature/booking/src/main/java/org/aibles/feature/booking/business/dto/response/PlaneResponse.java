package org.aibles.feature.booking.business.dto.response;

import java.util.List;
import org.aibles.feature.booking.entity.Plane;

public class PlaneResponse {

  private String id;
  private String model;
  private String description;
  private List<BenefitResponse> benefits;

  public PlaneResponse(String id, String model, String description,
      List<BenefitResponse> benefits) {
    this.id = id;
    this.model = model;
    this.description = description;
    this.benefits = benefits;
  }

  public PlaneResponse() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<BenefitResponse> getBenefits() {
    return benefits;
  }

  public void setBenefits(
      List<BenefitResponse> benefits) {
    this.benefits = benefits;
  }

  public static PlaneResponse from(Plane plane) {
    PlaneResponse response = new PlaneResponse();
    response.setId(plane.getId());
    response.setModel(plane.getModel());
    response.setDescription(plane.getDescription());
    return response;
  }

}
