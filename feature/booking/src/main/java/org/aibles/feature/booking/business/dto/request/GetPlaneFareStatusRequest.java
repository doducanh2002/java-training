package org.aibles.feature.booking.business.dto.request;

public class GetPlaneFareStatusRequest {
  private String flightId;
  private String fareId;

  public GetPlaneFareStatusRequest(String flightId, String fareId) {
    this.flightId = flightId;
    this.fareId = fareId;
  }

  public GetPlaneFareStatusRequest() {
  }

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getFareId() {
    return fareId;
  }

  public void setFareId(String fareId) {
    this.fareId = fareId;
  }
}
