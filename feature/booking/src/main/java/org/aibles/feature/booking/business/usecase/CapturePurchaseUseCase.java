package org.aibles.feature.booking.business.usecase;

import org.aibles.business.dto.response.PaypalCaptureResponse;

public interface CapturePurchaseUseCase {
    PaypalCaptureResponse invoke(String token);
}
