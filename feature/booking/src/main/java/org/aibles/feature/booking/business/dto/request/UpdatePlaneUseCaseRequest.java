package org.aibles.feature.booking.business.dto.request;

import java.util.List;

public class UpdatePlaneUseCaseRequest {

  private String model;
  private String description;
  private List<String> benefitsId;

  public UpdatePlaneUseCaseRequest() {
  }

  public UpdatePlaneUseCaseRequest(String model, String description, List<String> benefitsId) {
    this.model = model;
    this.description = description;
    this.benefitsId = benefitsId;
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

  public List<String> getBenefitsId() {
    return benefitsId;
  }

  public void setBenefitsId(List<String> benefitsId) {
    this.benefitsId = benefitsId;
  }

  public static UpdatePlaneUseCaseRequest.UpdatePlaneUseCaseRequestBuilder builder() {
    return new UpdatePlaneUseCaseRequest.UpdatePlaneUseCaseRequestBuilder();
  }

  public static class UpdatePlaneUseCaseRequestBuilder {

    private String model;
    private String description;
    private List<String> benefitsId;

    public UpdatePlaneUseCaseRequestBuilder() {
    }

    public UpdatePlaneUseCaseRequest.UpdatePlaneUseCaseRequestBuilder model(String model) {
      this.model = model;
      return this;
    }

    public UpdatePlaneUseCaseRequest.UpdatePlaneUseCaseRequestBuilder description(String description) {
      this.description = description;
      return this;
    }

    public UpdatePlaneUseCaseRequest.UpdatePlaneUseCaseRequestBuilder benefitsId(List<String> benefitsId) {
      this.benefitsId = benefitsId;
      return this;
    }

    public UpdatePlaneUseCaseRequest build() {
      return new UpdatePlaneUseCaseRequest(model, description, benefitsId);
    }
  }
}
