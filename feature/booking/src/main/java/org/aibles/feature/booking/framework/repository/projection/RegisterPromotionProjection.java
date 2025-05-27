package org.aibles.feature.booking.framework.repository.projection;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPromotionProjection {

  private Long archivedMiles;
  private LocalDateTime giveAwayDay;
}
