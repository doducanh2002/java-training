package org.aibles.feature.booking.business.usecase;

import org.aibles.business.dto.paypal.PaypalOrderSimple;
import org.aibles.feature.booking.business.dto.request.PurchaseUseCaseRequest;

public interface PurchaseUseCase {

  PaypalOrderSimple invoke(PurchaseUseCaseRequest request);
}
