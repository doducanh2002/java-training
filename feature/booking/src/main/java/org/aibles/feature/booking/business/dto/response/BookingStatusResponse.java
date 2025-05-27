package org.aibles.feature.booking.business.dto.response;

import java.util.List;

public class BookingStatusResponse {

  private List<SeatStatusResponse> seat;

  public BookingStatusResponse() {
  }

  public BookingStatusResponse(List<SeatStatusResponse> seat) {
    this.seat = seat;
  }

  public List<SeatStatusResponse> getSeat() {
    return seat;
  }

  public void setSeat(
      List<SeatStatusResponse> seat) {
    this.seat = seat;
  }
}
