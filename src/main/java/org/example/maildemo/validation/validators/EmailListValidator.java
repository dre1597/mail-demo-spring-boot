package org.example.maildemo.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.maildemo.validation.constraints.IsEmailList;

import java.util.List;

public class EmailListValidator implements ConstraintValidator<IsEmailList, List<String>> {
  private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

  @Override
  public boolean isValid(List<String> emails, ConstraintValidatorContext context) {
    return emails.stream().allMatch(email -> email.matches(EMAIL_REGEX));
  }
}
