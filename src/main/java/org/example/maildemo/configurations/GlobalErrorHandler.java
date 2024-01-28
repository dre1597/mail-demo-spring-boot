package org.example.maildemo.configurations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<CustomError> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
        .map(error -> new CustomError(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
  }
}
