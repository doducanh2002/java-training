package org.aibles.feature.booking.business.dto.response;

import java.util.List;

public class AvailabilitySeatResponse {

  private boolean isAvailable;
  private List<String> seats;

  public AvailabilitySeatResponse(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public AvailabilitySeatResponse(List<String> seats, boolean isAvailable) {
    this.seats = seats;
    this.isAvailable = isAvailable;
  }

  public List<String> getSeats() {
    return seats;
  }

  public void setSeats(List<String> seats) {
    this.seats = seats;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }
}
