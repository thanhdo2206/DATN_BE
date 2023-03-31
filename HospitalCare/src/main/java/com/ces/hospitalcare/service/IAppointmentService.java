package com.ces.hospitalcare.service;
import com.ces.hospitalcare.dto.AppointmentDTO;
import java.util.List;

public interface IAppointmentService {
  List<AppointmentDTO> getAllAppointmentOfDoctor(Long doctorId);

  List<AppointmentDTO> getAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

  AppointmentDTO changeStatusByDoctor(AppointmentDTO appointmentChangeStatusDTO, Long doctorId);
}
