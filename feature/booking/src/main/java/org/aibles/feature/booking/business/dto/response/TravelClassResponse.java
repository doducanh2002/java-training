package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.TravelClass;

public class TravelClassResponse {
  private String id;
  private String name;
  private String description;

  public TravelClassResponse(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public TravelClassResponse() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static TravelClassResponse from(TravelClass travelClass){
    TravelClassResponse travelClassResponse = new TravelClassResponse();
    travelClassResponse.setId(travelClass.getId());
    travelClassResponse.setDescription(travelClass.getDescription());
    travelClassResponse.setName(travelClass.getName());
    return travelClassResponse;
  }
}
