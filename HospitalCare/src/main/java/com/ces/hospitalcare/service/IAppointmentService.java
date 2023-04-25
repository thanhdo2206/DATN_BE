package com.ces.hospitalcare.service;

import com.ces.hospitalcare.dto.AppointmentDTO;
import com.ces.hospitalcare.http.request.AppointmentRequest;
import com.ces.hospitalcare.http.response.AppointmentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAppointmentService {
    List<AppointmentResponse> getListAppointmentOfPatient();

    int countByStatusAndDoctorId(int status, Long doctorId);

    List<AppointmentDTO> getAllAppointmentOfDoctorPageableByStatusAndDoctorId(int appointmentStatus,
                                                                              Long doctorId, Pageable pageable);


    List<AppointmentDTO> getAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

    AppointmentDTO changeStatusByDoctor(AppointmentDTO appointmentChangeStatusDTO, Long doctorId);

    AppointmentDTO bookAppointmentByPatient(AppointmentRequest appointmentRequest);
}
