package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class PromotionCodeNotFoundException extends NotFoundException {

  public PromotionCodeNotFoundException(String code) {
    setCode("org.aibles.feature.booking.business.exception.PromotionCodeNotFoundException");
    addParams("code", code);
  }
}
