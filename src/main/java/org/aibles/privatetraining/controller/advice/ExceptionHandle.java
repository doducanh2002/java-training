package org.aibles.privatetraining.controller.advice;

import org.aibles.privatetraining.exception.BaseException;
import org.aibles.privatetraining.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(value = {BaseException.class})
  public ResponseEntity<ResponseException> exceptionHandle(BaseException error) {
    ResponseException er = new ResponseException();
    er.setError(String.valueOf(error.getStatus()));
    er.setMessage(error.getParams().toString());
    er.setTimestamp(Instant.now());
    return ResponseEntity.status(HttpStatus.valueOf(error.getStatus())).body(er);
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseException validationExceptionHandle(MethodArgumentNotValidException exception) {
    String errorMessage = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ResponseException re = new ResponseException();
    re.setError("Validation Exception");
    re.setMessage(errorMessage);
    re.setTimestamp(Instant.now());
    return re;
  }
}