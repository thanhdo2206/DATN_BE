package com.ces.hospitalcare.security.config;
public class SecurityContact {
  public static final long EXPIRATION_TIME = 864000000; // 10 days

  public static final String TOKEN_PREFIX = "Bearer ";

  public static final String HEADER_STRING = "Authorization";

  public static final String SIGN_IN_URL = "/login";

  public static final String SIGN_UP_URL = "/users";

  public static final String SECRET_KEY = "66556A586E327235753878214125442A472D4B6150645367566B597033733676";

  public static final String HEADER_USERID = "UserId";
}