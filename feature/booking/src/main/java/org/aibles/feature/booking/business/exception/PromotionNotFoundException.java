package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class PromotionNotFoundException extends NotFoundException {

  public PromotionNotFoundException(String promotionId) {
    setCode("org.aibles.feature.booking.business.exception.PromotionNotFoundException");
    addParams("promotionId", promotionId);
  }
}
