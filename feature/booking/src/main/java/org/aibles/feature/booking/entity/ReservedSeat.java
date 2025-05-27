package org.aibles.feature.booking.entity;

public class ReservedSeat {

  private String id;
  private ReservedTrip reservedTrip;
  private Seat seat;

  public ReservedSeat() {
  }

  public ReservedSeat(String id, ReservedTrip reservedTrip, Seat seat) {
    this.id = id;
    this.reservedTrip = reservedTrip;
    this.seat = seat;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReservedTrip getReservedTrip() {
    return reservedTrip;
  }

  public void setReservedTrip(ReservedTrip reservedTrip) {
    this.reservedTrip = reservedTrip;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }
}
