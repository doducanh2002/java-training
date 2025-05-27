package org.aibles.feature.booking.framework.controller.dto.request.calculateprice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceUseCaseRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePriceRequest {

  @NotNull
  @Size(min = 1, max = 2)
  private List<CalculatePriceTripRequest> trips;

  private int numOfPassengers;

  private String promotionId;

  public static CalculatePriceUseCaseRequest to(final CalculatePriceRequest calculatePriceRequest) {
    CalculatePriceUseCaseRequest calculatePriceUseCaseRequest = new CalculatePriceUseCaseRequest();
    calculatePriceUseCaseRequest.setTrips(
        calculatePriceRequest.getTrips().stream()
            .map(CalculatePriceTripRequest::to).toList()
    );
    calculatePriceUseCaseRequest.setNumOfPassengers(calculatePriceRequest.getNumOfPassengers());
    calculatePriceUseCaseRequest.setPromotionId(calculatePriceRequest.getPromotionId());
    return calculatePriceUseCaseRequest;
  }
}
