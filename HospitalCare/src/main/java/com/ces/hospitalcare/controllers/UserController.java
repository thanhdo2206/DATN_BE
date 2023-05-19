package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.request.DoctorRequest;
import com.ces.hospitalcare.http.request.DoctorUpdateRequest;
import com.ces.hospitalcare.http.request.UpdateUserProfileRequest;
import com.ces.hospitalcare.http.response.DoctorResponse;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.IUserService;
import java.io.IOException;
import java.util.List;
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
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
public class UserController {
  @Autowired
  private IUserService userService;

  @GetMapping("")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
  public ResponseEntity<UserResponse> getCurrentUser() {
    UserResponse userResponse = userService.getCurrentUser();
    return ResponseEntity.status(HttpStatus.OK).body(userResponse);
  }

  @GetMapping("/patient")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public List<UserDTO> getAllPatient() {

    return userService.getAllPatient();
  }

  @GetMapping("/doctor")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public List<DoctorResponse> getAllDoctorArchive(
      @RequestParam(value = "statusArchive") int statusArchive) {

    return userService.getAllDoctor(statusArchive);
  }

  @GetMapping("/patient/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public UserDTO getDetailPatient(@PathVariable("id") Long userId) {

    return userService.getDetailUser(userId);
  }

  @GetMapping("/doctor/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public DoctorResponse getDetailDoctor(@PathVariable("id") Long doctorId) {

    return userService.getDetailDoctor(doctorId);
  }

  @PostMapping("/doctor")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public DoctorResponse addDoctor(@RequestBody DoctorRequest doctorRequest) {

    return userService.addDoctor(doctorRequest);
  }

  @PostMapping("/doctor/check-email")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public String checkEmailDoctor(@RequestBody UserDTO doctorDTO) {

    return userService.checkEmailDoctor(doctorDTO);
  }

  @PutMapping("/doctor/update-profile/{doctorId}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public UserDTO updateDoctorProfile(
      @PathVariable Long doctorId,
      @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
    doctorUpdateRequest.setDoctorId(doctorId);
    return userService.updateProfileDoctor(doctorUpdateRequest);
  }

  @PostMapping("/{userId}/profile-picture")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
  public ResponseEntity<UserDTO> updateProfilePicture(
      @PathVariable Long userId,
      @RequestParam("profilePicture") MultipartFile multipartFile) throws IOException {
    UserDTO userDTO = userService.updateUserProfilePicture(userId, multipartFile);
    return ResponseEntity.status(HttpStatus.OK).body(userDTO);
  }

  @PutMapping("/{userId}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PATIENT', 'ROLE_DOCTOR')")
  public ResponseEntity<UserDTO> updateUserProfile(
      @PathVariable Long userId,
      @RequestBody UpdateUserProfileRequest updateUserProfileRequest) throws IOException {
    UserDTO userDTO = userService.updateUserProfile(userId, updateUserProfileRequest);
    return ResponseEntity.status(HttpStatus.OK).body(userDTO);
  }
}
