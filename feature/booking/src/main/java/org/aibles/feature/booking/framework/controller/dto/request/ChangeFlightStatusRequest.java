package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangeFlightStatusRequest {
    @NotNull(message = "Status is required")
    @Pattern(regexp = "ON_TIME|DELAYED|DEPARTED", message = "Status must be ON_TIME, DELAYED, or DEPARTED")
    private String status;
}
