package org.aibles.feature.customer.business.usecase;

import org.aibles.feature.customer.business.dto.request.RefreshAccessTokenUseCaseRequest;
import org.aibles.feature.customer.business.dto.response.RefreshAccessTokenUseCaseResponse;

public interface RefreshAccessTokenUseCase {
  RefreshAccessTokenUseCaseResponse invoke(RefreshAccessTokenUseCaseRequest request);
}
