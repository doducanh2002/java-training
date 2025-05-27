package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAirportRequest {

  @NotBlank(message = "name is required")
  private String name;
  @NotBlank(message = "code is required")
  private String code;
  @NotBlank(message = "country is required")
  private String country;
  @NotBlank(message = "region is required")
  private String region;
}
