package org.aibles.feature.booking.business.dto.request;

public class UpdateAirportUseCaseRequest {

  private String name;
  private String code;
  private String country;
  private String region;

  public UpdateAirportUseCaseRequest() {
  }

  public UpdateAirportUseCaseRequest(String name, String code, String country, String region) {
    this.name = name;
    this.code = code;
    this.country = country;
    this.region = region;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public static UpdateAirportUseCaseRequestBuilder builder() {
    return new UpdateAirportUseCaseRequestBuilder();
  }

  public static class UpdateAirportUseCaseRequestBuilder {

    private String name;
    private String code;
    private String country;
    private String region;

    public UpdateAirportUseCaseRequestBuilder() {
    }

    public UpdateAirportUseCaseRequestBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UpdateAirportUseCaseRequestBuilder code(String code) {
      this.code = code;
      return this;
    }

    public UpdateAirportUseCaseRequestBuilder country(String country) {
      this.country = country;
      return this;
    }

    public UpdateAirportUseCaseRequestBuilder region(String region) {
      this.region = region;
      return this;
    }

    public UpdateAirportUseCaseRequest build() {
      return new UpdateAirportUseCaseRequest(name, code, country, region);
    }
  }
}
