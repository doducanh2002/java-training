package org.aibles.feature.booking.framework.controller.dto.request.calculateprice;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceFlightUseCaseRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePriceFlightRequest {

  @NotBlank
  private String id;

  @NotBlank
  private String mealId;

  public static CalculatePriceFlightUseCaseRequest
  to(final CalculatePriceFlightRequest calculatePriceFlightRequest) {
    CalculatePriceFlightUseCaseRequest request = new CalculatePriceFlightUseCaseRequest();
    request.setId(calculatePriceFlightRequest.getId());
    request.setMealId(calculatePriceFlightRequest.getMealId());
    return request;
  }
}
