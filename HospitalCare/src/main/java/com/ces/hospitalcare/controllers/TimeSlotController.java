package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.response.TimeSlotResponse;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.service.ITimeSlotService;
import com.ces.hospitalcare.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/time_slots")
public class TimeSlotController {
  @Autowired
  private ITimeSlotService timeSlotService;

  @Autowired
  private IUserService userService;

  @GetMapping(value = "/guest/examination")
  public List<TimeSlotResponse> getAllTimeSlotByExaminationId(
      @RequestParam("examinationId") Long medicalExaminationId) {

    return timeSlotService.getAllTimeSlotByExaminationId(medicalExaminationId);
  }

  @GetMapping(value = "/guest/{id}")
  public TimeSlotResponse getDetailTimeSlot(
      @PathVariable("id") Long timeSlotId) {
    return timeSlotService.getDetailTimeSlot(timeSlotId);
  }

  @GetMapping(value = "/doctor")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
  public List<TimeSlotResponse> getAllTimeSlotOfDoctor() {
    UserResponse userResponse = userService.findEmailByToken();
    UserDTO userDTO = userResponse.getUser();

    return timeSlotService.getAllTimeSlotsOfCurrentWeek(userDTO.getId());
  }
}
