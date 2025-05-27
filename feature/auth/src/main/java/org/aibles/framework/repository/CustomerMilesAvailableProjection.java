package org.aibles.framework.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMilesAvailableProjection {

  private String id;
  private Long milesAvailable;

}
