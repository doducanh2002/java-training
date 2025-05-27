package org.aibles.feature.booking.business.dto.response.planedetail;

public class SeatResponse {

  private String id;
  private Integer seatRow;
  private String seatColumn;
  private Integer seatPitch;
  private Integer seatWidth;

  public SeatResponse() {
  }

  public SeatResponse(String id, Integer seatRow, String seatColumn, Integer seatPitch,
      Integer seatWidth) {
    this.id = id;
    this.seatRow = seatRow;
    this.seatColumn = seatColumn;
    this.seatPitch = seatPitch;
    this.seatWidth = seatWidth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(Integer seatRow) {
    this.seatRow = seatRow;
  }

  public String getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(String seatColumn) {
    this.seatColumn = seatColumn;
  }

  public Integer getSeatPitch() {
    return seatPitch;
  }

  public void setSeatPitch(Integer seatPitch) {
    this.seatPitch = seatPitch;
  }

  public Integer getSeatWidth() {
    return seatWidth;
  }

  public void setSeatWidth(Integer seatWidth) {
    this.seatWidth = seatWidth;
  }
}
