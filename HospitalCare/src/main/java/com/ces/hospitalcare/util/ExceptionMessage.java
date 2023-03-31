package com.ces.hospitalcare.util;
public enum ExceptionMessage {
  USERNAME_PASSWORD_INVALIDATE("Username or Password Incorrect"),
  EMAIL_ALREADY_EXIST("Email Already Exist");

  private String message;

  ExceptionMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

