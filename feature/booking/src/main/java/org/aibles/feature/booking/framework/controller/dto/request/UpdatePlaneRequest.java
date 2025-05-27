package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
public class UpdatePlaneRequest {

  @NotBlank(message = "model is required")
  private String model;

  @NotBlank(message = "description is required")
  private String description;

  @NotNull(message = "benefitsId is required")
  @Size(min = 1, message = "The list of benefitsId must contain at least one element.")
  private List<String> benefitsId;
}
