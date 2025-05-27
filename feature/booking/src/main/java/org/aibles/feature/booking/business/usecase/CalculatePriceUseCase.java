package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.calculateprice.CalculatePriceUseCaseRequest;
import org.aibles.feature.booking.business.dto.response.CalculatePriceResponse;

public interface CalculatePriceUseCase {

  CalculatePriceResponse invoke(CalculatePriceUseCaseRequest request);
}
