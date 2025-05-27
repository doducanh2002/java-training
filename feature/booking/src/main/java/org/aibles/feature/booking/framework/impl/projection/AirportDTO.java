package org.aibles.feature.booking.framework.impl.projection;

public class AirportDTO implements AirportProjection {
  private String id;
  private String name;
  private String code;
  private String country;
  private String region;

  public AirportDTO(String id, String name, String code, String country, String region) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.country = country;
    this.region = region;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public String getRegion() {
    return region;
  }
}
