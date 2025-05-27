package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.response.CustomerDetailResponse;

public interface CustomerDetailUseCase {

  CustomerDetailResponse invoke(String credential);
}
