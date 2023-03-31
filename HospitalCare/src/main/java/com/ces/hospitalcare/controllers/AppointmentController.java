package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.dto.AppointmentDTO;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.IAppointmentService;
import com.ces.hospitalcare.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/appointments")
public class AppointmentController {
  @Autowired
  private IAppointmentService appointmentService;

  @Autowired
  private IUserService userService;

  @GetMapping(path = "/doctor")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
  public List<AppointmentDTO> getAllAppointmentOfDoctor() {
    UserResponse userResponse = userService.findEmailByToken();
    UserDTO userDTO = userResponse.getUser();
    return appointmentService.getAllAppointmentOfDoctor(userDTO.getId());
  }

  @GetMapping(path = "/doctor/patient")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
  public List<AppointmentDTO> getAllByDoctorIdAndPatientId(
      @RequestParam(value = "patientId") Long patientId) {
    UserResponse userResponse = userService.findEmailByToken();
    UserDTO userDTO = userResponse.getUser();
    return appointmentService.getAllByDoctorIdAndPatientId(userDTO.getId(), patientId);
  }

  @PatchMapping(path = "/change_status_appointment/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
  public AppointmentDTO changeStatusAppointmentByDoctor(
      @PathVariable(value = "id") Long appointmentId, @RequestBody AppointmentDTO appointmentDTO) {
    UserResponse userResponse = userService.findEmailByToken();
    UserDTO userDTO = userResponse.getUser();
    appointmentDTO.setId(appointmentId);

    return appointmentService.changeStatusByDoctor(appointmentDTO, userDTO.getId());
  }
}
