package org.aibles.feature.booking.framework.controller.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSeatsRequest {

  private List<String> seats;
}
