package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PromotionAlreadyUsedException extends BadRequestException {

  public PromotionAlreadyUsedException() {
    setCode("org.aibles.feature.booking.business.exception.PromotionAlreadyUsedException");
  }

}
