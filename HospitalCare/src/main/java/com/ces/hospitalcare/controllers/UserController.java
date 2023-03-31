package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
  @Autowired
  private IUserService userService;

  @GetMapping("/user")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
  public ResponseEntity<UserResponse> getCurrentUserByToken() {
    UserResponse userResponse = userService.findEmailByToken();
    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }
}
