package org.aibles.feature.booking.framework.controller.dto.request.calculateprice;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceTripUseCaseRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePriceTripRequest {

  @NotBlank
  private String id;

  @NotBlank
  private String fareId;

  private List<CalculatePriceFlightRequest> flights = new ArrayList<>();

  public static CalculatePriceTripUseCaseRequest to(final CalculatePriceTripRequest calculatePriceTripRequest) {
    CalculatePriceTripUseCaseRequest request = new CalculatePriceTripUseCaseRequest();
    request.setId(calculatePriceTripRequest.getId());
    request.setFareId(calculatePriceTripRequest.getFareId());
    request.setFlights(calculatePriceTripRequest.getFlights()
        .stream().map(CalculatePriceFlightRequest::to).toList());
    return request;
  }
}
