package org.aibles.feature.booking.framework.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidatorHelper {

  private static final Logger log = LoggerFactory.getLogger(ValidatorHelper.class);
  private final Validator validator;

  public ValidatorHelper(Validator validator) {
    this.validator = validator;
  }

  public void validate(Object request) {
    log.info("(validate)request: {}", request);
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
  }
}
