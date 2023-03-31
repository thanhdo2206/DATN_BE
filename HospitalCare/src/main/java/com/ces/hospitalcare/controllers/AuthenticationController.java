package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.http.request.AuthenticationRequest;
import com.ces.hospitalcare.http.request.RegisterRequest;
import com.ces.hospitalcare.http.response.LoginResponse;
import com.ces.hospitalcare.http.response.RegisterResponse;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/guest")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private final IAuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(
      @RequestBody RegisterRequest request
  ) {
    RegisterResponse registeredUser = authenticationService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<UserResponse> authenticate(
      @RequestBody AuthenticationRequest requestAuth
  ) {
    LoginResponse loginUser = authenticationService.authenticate(requestAuth);
    var token = loginUser.getToken();
    var authResponse = loginUser.getAuthResponse();
    return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.SET_COOKIE, token)
        .body(authResponse);
  }

  @GetMapping("/demo")
  public ResponseEntity<String> sayHellsso(HttpServletRequest request) {
    System.out.println(request.getRemoteAddr());
    return ResponseEntity.ok("Hello from public endpoint user");
  }
}