package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FilterTripsRequest {
  @Pattern(regexp = "ONE_WAY|ROUND_TRIP")
  private String type;

  @NotNull
  private Object filter;
}
