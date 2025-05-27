package org.aibles.feature.booking.business.dto.response.planedetail;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.PlaneResponse;

public class PlaneDetailResponse {

  private PlaneResponse plane;
  private List<TravelClassDetailResponse> travelClasses;

  public PlaneDetailResponse() {
  }

  public PlaneDetailResponse(PlaneResponse plane, List<TravelClassDetailResponse> travelClasses) {
    this.plane = plane;
    this.travelClasses = travelClasses;
  }

  public PlaneResponse getPlane() {
    return plane;
  }

  public void setPlane(PlaneResponse plane) {
    this.plane = plane;
  }

  public List<TravelClassDetailResponse> getTravelClasses() {
    return travelClasses;
  }

  public void setTravelClasses(
      List<TravelClassDetailResponse> travelClasses) {
    this.travelClasses = travelClasses;
  }
}
