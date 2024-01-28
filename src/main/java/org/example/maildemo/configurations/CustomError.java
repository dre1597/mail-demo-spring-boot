package org.example.maildemo.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class CustomError {
  @JsonInclude(NON_NULL)
  @JsonProperty("field")
  private String field;

  @JsonInclude(NON_NULL)
  @JsonProperty("message")
  private String message;

  public CustomError(final FieldError error) {
    this.field = error.getField();
    this.message = error.getDefaultMessage();
  }

  public CustomError(final ObjectError error) {
    this.message = error.getDefaultMessage();
  }

  public CustomError(final String field, final String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public void setField(final String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
