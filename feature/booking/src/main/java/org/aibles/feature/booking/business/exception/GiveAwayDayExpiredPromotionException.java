package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class GiveAwayDayExpiredPromotionException extends BadRequestException {

  public GiveAwayDayExpiredPromotionException(String id) {
    setCode("org.aibles.feature.booking.business.exception.GiveAwayDayExpiredPromotionException");
    addParams("id", id);
  }
}
