package com.ces.hospitalcare.http.exception;
public class AlreadyExistException extends RuntimeException {
  public AlreadyExistException() {
    super();
  }

  public AlreadyExistException(String message) {
    super(message);
  }

  public AlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public AlreadyExistException(Throwable cause) {
    super(cause);
  }

  protected AlreadyExistException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
