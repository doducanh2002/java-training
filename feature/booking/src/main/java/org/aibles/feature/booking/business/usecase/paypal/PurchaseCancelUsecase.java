package org.aibles.feature.booking.business.usecase.paypal;

public interface PurchaseCancelUsecase {
  void invoke(String paypalToken);
}
