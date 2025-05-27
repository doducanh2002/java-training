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

@Documented
@Constraint(validatedBy = ValidateConfirmationCode.ConfirmationCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidateConfirmationCode {

  String message() default "ConfirmationCode must be exactly 6 characters long and contain only "
      + "uppercase letters or a combination of uppercase letters and numbers!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class ConfirmationCodeValidator implements ConstraintValidator<ValidateConfirmationCode, String> {

    @Override
    public void initialize(ValidateConfirmationCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String confirmationCode, ConstraintValidatorContext context) {
      if (confirmationCode == null || confirmationCode.isEmpty()) {
        return true;
      }
      String regexConfirmationCode = "^([A-Z]{6}|[A-Z0-9]{6})$";
      return confirmationCode.matches(regexConfirmationCode);
    }
  }
}
