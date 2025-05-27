package org.aibles.feature.booking.business.dto.request;

import java.util.List;

public class CreatePlaneUseCaseRequest {

  private String model;
  private String description;
  private List<String> benefitsId;

  public CreatePlaneUseCaseRequest() {
  }

  public CreatePlaneUseCaseRequest(String model, String description, List<String> benefitsId) {
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

  public static CreatePlaneUseCaseRequest.CreatePlaneUseCaseRequestBuilder builder() {
    return new CreatePlaneUseCaseRequest.CreatePlaneUseCaseRequestBuilder();
  }

  public static class CreatePlaneUseCaseRequestBuilder {

    private String model;
    private String description;
    private List<String> benefitsId;

    public CreatePlaneUseCaseRequestBuilder() {
    }

    public CreatePlaneUseCaseRequest.CreatePlaneUseCaseRequestBuilder model(String model) {
      this.model = model;
      return this;
    }

    public CreatePlaneUseCaseRequest.CreatePlaneUseCaseRequestBuilder description(String description) {
      this.description = description;
      return this;
    }

    public CreatePlaneUseCaseRequest.CreatePlaneUseCaseRequestBuilder benefitsId(List<String> benefitsId) {
      this.benefitsId = benefitsId;
      return this;
    }

    public CreatePlaneUseCaseRequest build() {
      return new CreatePlaneUseCaseRequest(model, description, benefitsId);
    }
  }
}
