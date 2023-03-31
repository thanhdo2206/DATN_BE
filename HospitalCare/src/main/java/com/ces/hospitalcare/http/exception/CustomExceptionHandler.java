package com.ces.hospitalcare.http.exception;
import com.ces.hospitalcare.http.response.ErrorResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(LoginUserException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleLoginUserException(Exception ex) {
    return new ErrorResponse(ex.getMessage(), new Date(), HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler(AlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleAlreadyExistException(Exception ex) {
    return new ErrorResponse(ex.getMessage(), new Date(), HttpStatus.CONFLICT.value());
  }

  @ExceptionHandler({IOException.class, ServerException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handleDoFilterException(Exception ex) {
    return new ErrorResponse(ex.getMessage(), new Date(), HttpStatus.FORBIDDEN.value());
  }
}