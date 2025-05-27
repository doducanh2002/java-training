package org.aibles.bussiness.dto;

public class ImportSeatDTO {

  private String id;
  private int capacity;
  private int seatRow;
  private String seatColumn;
  private int seatPitch;
  private int seatWidth;
  private String nameTravelClass;

  public ImportSeatDTO() {
  }

  public ImportSeatDTO(String id, int capacity, int seatRow, String seatColumn,
      int seatPitch, int seatWidth, String nameTravelClass) {
    this.id = id;
    this.capacity = capacity;
    this.seatRow = seatRow;
    this.seatColumn = seatColumn;
    this.seatPitch = seatPitch;
    this.seatWidth = seatWidth;
    this.nameTravelClass = nameTravelClass;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getSeatRow() {
    return seatRow;
  }

  public void setSeatRow(int seatRow) {
    this.seatRow = seatRow;
  }

  public String getSeatColumn() {
    return seatColumn;
  }

  public void setSeatColumn(String seatColumn) {
    this.seatColumn = seatColumn;
  }

  public int getSeatPitch() {
    return seatPitch;
  }

  public void setSeatPitch(int seatPitch) {
    this.seatPitch = seatPitch;
  }

  public int getSeatWidth() {
    return seatWidth;
  }

  public void setSeatWidth(int seatWidth) {
    this.seatWidth = seatWidth;
  }

  public String getNameTravelClass() {
    return nameTravelClass;
  }

  public void setNameTravelClass(String nameTravelClass) {
    this.nameTravelClass = nameTravelClass;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
