package org.aibles.feature.booking.framework.impl.projection;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightPlaneProjection {

  private String flightId;
  private String flightNumber;
  private String airline;
  private String departureAirportName;
  private String arrivalAirportName;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private boolean isStopStation;
  private Long miles;
  private String planeId;
  private String model;
  private String status;
  private String tripId;
}
