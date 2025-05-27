package org.aibles.framework.validation;

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
import org.aibles.framework.constant.Gender;

@Constraint(validatedBy = ValidateGender.GenderValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidateGender {

  String message() default "Invalid gender!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class GenderValidation implements ConstraintValidator<ValidateGender, String> {

    @Override
    public void initialize(ValidateGender constraintAnnotation) {}

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
      if (gender == null || gender.isEmpty()) {
        return true;
      }
      return Arrays.stream(Gender.values())
          .anyMatch(genderValue -> genderValue.name().equalsIgnoreCase(gender));
    }
  }
}
