package com.ces.hospitalcare.service;
import com.ces.hospitalcare.http.request.AuthenticationRequest;
import com.ces.hospitalcare.http.request.RegisterRequest;
import com.ces.hospitalcare.http.response.LoginResponse;
import com.ces.hospitalcare.http.response.RegisterResponse;

public interface IAuthenticationService {
  RegisterResponse register(RegisterRequest request);

  LoginResponse authenticate(AuthenticationRequest request);
}
