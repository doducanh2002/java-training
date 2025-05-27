package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PromotionExpiredLuckyDrawException extends BadRequestException {

  public PromotionExpiredLuckyDrawException() {
    setCode("org.aibles.feature.booking.business.exception.PromotionExpiredLuckyDrawException");
  }
}
