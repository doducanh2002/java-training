package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaneFareStatusRequest {
  @NotBlank(message = "flightId is required")
  private String flightId;
  @NotBlank(message = "fareId is required")
  private String fareId;
}
