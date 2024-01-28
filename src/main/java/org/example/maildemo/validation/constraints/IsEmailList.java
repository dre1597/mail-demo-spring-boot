package org.example.maildemo.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.maildemo.validation.validators.EmailListValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailListValidator.class)
public @interface IsEmailList {
  String message() default "Please, enter a valid email list";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
