package org.aibles.feature.booking.business.dto.response;

import java.util.ArrayList;
import java.util.List;
import org.aibles.feature.booking.framework.controller.dto.response.SeatStatusDTO;

public class SeatStatusResponse {

  private String id;
  private int seatRow;
  private String seatColumn;
  private int seatPitch;
  private int seatWidth;
  private boolean isReserved;

  public SeatStatusResponse(String id, int seatRow, String seatColumn, int seatPitch, int seatWidth,
      boolean isReserved) {
    this.id = id;
    this.seatRow = seatRow;
    this.seatColumn = seatColumn;
    this.seatPitch = seatPitch;
    this.seatWidth = seatWidth;
    this.isReserved = isReserved;
  }

  public SeatStatusResponse() {
  }

  public static List<SeatStatusResponse> from(List<SeatStatusDTO> seatStatusDTOS) {
    List<SeatStatusResponse> list = new ArrayList<>();
    for (SeatStatusDTO seatStatusDTO : seatStatusDTOS) {
      SeatStatusResponse SeatStatusResponse = new SeatStatusResponse();
      SeatStatusResponse.setId(seatStatusDTO.getId());
      SeatStatusResponse.setSeatRow(seatStatusDTO.getSeatRow());
      SeatStatusResponse.setSeatColumn(seatStatusDTO.getSeatColumn());
      SeatStatusResponse.setSeatPitch(seatStatusDTO.getSeatPitch());
      SeatStatusResponse.setSeatWidth(seatStatusDTO.getSeatWidth());
      SeatStatusResponse.setReserved(seatStatusDTO.isReserved());
      list.add(SeatStatusResponse);
    }
    return list;
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

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }
}
