package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.request.ChangeFlightStatusUsecaseRequest;

public interface ChangeStatusFlightUseCase {
    void invoke(ChangeFlightStatusUsecaseRequest request);
}
