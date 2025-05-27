package org.aibles.feature.booking.business.dto.response.planedetail;

import java.util.List;

public class TravelClassDetailResponse {

  private String id;
  private String name;
  private List<SeatResponse> seats;

  public TravelClassDetailResponse() {
  }

  public TravelClassDetailResponse(String id, String name, List<SeatResponse> seats) {
    this.id = id;
    this.name = name;
    this.seats = seats;
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

  public List<SeatResponse> getSeats() {
    return seats;
  }

  public void setSeats(List<SeatResponse> seats) {
    this.seats = seats;
  }

}
