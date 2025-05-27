package org.aibles.feature.booking.entity;

public class Airport {

  private String id;
  private String name;
  private String code;
  private String country;
  private String region;

  public Airport() {
  }

  public Airport(String id, String name, String code, String country, String region) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.country = country;
    this.region = region;
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }
}
