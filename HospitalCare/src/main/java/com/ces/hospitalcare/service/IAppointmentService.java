package com.ces.hospitalcare.service;
import com.ces.hospitalcare.dto.AppointmentDTO;
import com.ces.hospitalcare.http.request.AppointmentRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IAppointmentService {
  int countByStatusAndDoctorId(int status, Long doctorId);

  List<AppointmentDTO> getAllAppointmentOfDoctorPageableByStatusAndDoctorId(int appointmentStatus,
      Long doctorId, Pageable pageable);

  List<AppointmentDTO> getAllAppointmentOfDoctor(Long doctorId);

  List<AppointmentDTO> getAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

  AppointmentDTO changeStatusByDoctor(AppointmentDTO appointmentChangeStatusDTO, Long doctorId);

  AppointmentDTO bookAppointmentByPatient(AppointmentRequest appointmentRequest);
}
