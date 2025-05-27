package org.aibles.feature.booking.business.exception;

import org.aibles.business.exception.NotFoundException;

public class BenefitNotFoundException extends NotFoundException {

  public BenefitNotFoundException(String benefitId) {
    setCode("org.aibles.feature.booking.business.exception.BenefitNotFoundException");
    addParams("benefitId", benefitId);
  }
}
