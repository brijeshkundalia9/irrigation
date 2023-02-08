package com.irrigation.system.exception.handler;

import com.irrigation.system.exception.ConfigurationNotFoundException;
import com.irrigation.system.exception.InvalidStatusException;
import com.irrigation.system.exception.PlotNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({PlotNotFoundException.class, ConfigurationNotFoundException.class})
  public ResponseEntity<?> handleNotFoundExceptions(
      RuntimeException exception) {
    Errors error = new Errors();
    error.setError(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({InvalidStatusException.class})
  public ResponseEntity<?> handleNotInvalidStatusExceptions(
      RuntimeException exception) {
    Errors error = new Errors();
    error.setError(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Data
  private static class Errors {
    private String error;
  }

}
