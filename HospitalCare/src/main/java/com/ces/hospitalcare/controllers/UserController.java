package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.request.UpdateUserProfileRequest;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.IUserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
public class UserController {
  @Autowired
  private IUserService userService;

  @GetMapping("")
  public ResponseEntity<UserResponse> getCurrentUser() {
    UserResponse userResponse = userService.getCurrentUser();
    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }

  @PostMapping("/{userId}/profile-picture")
  public ResponseEntity<UserDTO> updateProfilePicture(
      @PathVariable Long userId,
      @RequestParam("profilePicture") MultipartFile multipartFile) throws IOException {
    UserDTO userDTO = userService.updateUserProfilePicture(userId, multipartFile);
    return ResponseEntity.status(HttpStatus.OK).body(userDTO);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUserProfile(
      @PathVariable Long userId,
      @RequestBody UpdateUserProfileRequest updateUserProfileRequest) throws IOException {
    UserDTO userDTO = userService.updateUserProfile(userId, updateUserProfileRequest);
    return ResponseEntity.status(HttpStatus.OK).body(userDTO);
  }
}
