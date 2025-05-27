package org.aibles.feature.booking.framework.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import org.aibles.feature.booking.constant.TripConstant;
import org.aibles.feature.booking.framework.constant.Gender;

@Constraint(validatedBy = ValidateTrip.GenderValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidateTrip {

  String message() default "Invalid trip!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class GenderValidation implements ConstraintValidator<ValidateTrip, String> {

    @Override
    public void initialize(ValidateTrip constraintAnnotation) {}

    @Override
    public boolean isValid(String tripType, ConstraintValidatorContext constraintValidatorContext) {
      if (tripType == null || tripType.isEmpty()) {
        return true;
      }
      return Arrays.stream(TripConstant.values())
          .anyMatch(tripValue -> tripValue.name().equalsIgnoreCase(tripType));
    }
  }
}
