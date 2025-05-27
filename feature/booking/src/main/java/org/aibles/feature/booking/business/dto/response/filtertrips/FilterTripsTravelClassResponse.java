package org.aibles.feature.booking.business.dto.response.filtertrips;

import java.util.List;

public class FilterTripsTravelClassResponse {
  private String id;
  private String name;
  private List<FilterTripsFareResponse> fares;

  public FilterTripsTravelClassResponse() {
  }

  public FilterTripsTravelClassResponse(String id, String name) {
    this.id = id;
    this.name = name;
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

  public List<FilterTripsFareResponse> getFares() {
    return fares;
  }

  public void setFares(
      List<FilterTripsFareResponse> fares) {
    this.fares = fares;
  }
}
