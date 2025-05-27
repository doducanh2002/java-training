package org.aibles.feature.booking.framework.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatStatusDTO {

  private String id;
  private int seatRow;
  private String seatColumn;
  private int seatPitch;
  private int seatWidth;
  private boolean isReserved;
}
