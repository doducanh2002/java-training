package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.BadRequestException;

public class PromotionCodeNotEligibleBadRequest extends BadRequestException {

  public PromotionCodeNotEligibleBadRequest(String userId, String code) {
    setCode("org.aibles.feature.booking.business.exception.PromotionCodeNotEligibleBadRequest");
    addParams("userId", userId);
    addParams("code", code);
  }
}
