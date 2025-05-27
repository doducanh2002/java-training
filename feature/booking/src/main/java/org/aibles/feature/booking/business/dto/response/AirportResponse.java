package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.Airport;

public class AirportResponse {
  private String id;
  private String name;
  private String code;
  private String country;
  private String region;

  public AirportResponse() {}

  public AirportResponse(String id, String name, String code, String country, String region) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.country = country;
    this.region = region;
  }

  public static AirportResponse from(Airport airport) {
    AirportResponse response = new AirportResponse();
    response.setId(airport.getId());
    response.setName(airport.getName());
    response.setCode(airport.getCode());
    response.setCountry(airport.getCountry());
    response.setRegion(airport.getRegion());
    return response;
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
}
