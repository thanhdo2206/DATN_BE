package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.AppointmentDTO;
import com.ces.hospitalcare.entity.AppointmentEntity;
import com.ces.hospitalcare.entity.TimeSlotEntity;
import com.ces.hospitalcare.entity.UserEntity;
import com.ces.hospitalcare.http.request.AppointmentRequest;
import com.ces.hospitalcare.repository.AppointmentRepository;
import com.ces.hospitalcare.repository.TimeSlotRepository;
import com.ces.hospitalcare.repository.UserRepository;
import com.ces.hospitalcare.service.IAppointmentService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TimeSlotRepository timeSlotRepository;

  @Autowired
  private ModelMapper mapper;

  public List<AppointmentDTO> createAppointmentDTO(
      List<AppointmentEntity> listAppointmentEntity) {
    List<AppointmentDTO> listAppointmentDTO = new ArrayList<>();

    for (AppointmentEntity entity : listAppointmentEntity) {

      AppointmentDTO dto = mapper.map(entity, AppointmentDTO.class);

      listAppointmentDTO.add(dto);
    }

    return listAppointmentDTO;
  }

  @Override
  public int countByStatusAndDoctorId(int status, Long doctorId) {
    return appointmentRepository.countByStatusAndDoctorId(status, doctorId);
  }

  @Override
  public List<AppointmentDTO> getAllAppointmentOfDoctorPageableByStatusAndDoctorId(
      int appointmentStatus, Long doctorId, Pageable pageable) {
    List<AppointmentEntity> listAppointmentEntity = appointmentRepository.findAllByStatusAndDoctorId(
        appointmentStatus, doctorId, pageable);

    return createAppointmentDTO(listAppointmentEntity);
  }

  @Override
  public List<AppointmentDTO> getAllAppointmentOfDoctor(Long doctorId) {
    List<AppointmentEntity> listAppointmentEntity = appointmentRepository.getAllAppointmentOfDoctorCurrentWeek(
        doctorId);
    return createAppointmentDTO(listAppointmentEntity);
  }

  @Override
  public List<AppointmentDTO> getAllByDoctorIdAndPatientId(Long doctorId, Long patientId) {

    return createAppointmentDTO(
        appointmentRepository.getAllByDoctorIdAndPatientId(doctorId, patientId));
  }

  @Override
  public AppointmentDTO changeStatusByDoctor(AppointmentDTO appointmentChangeStatusDTO,
      Long doctorId) {
    AppointmentEntity appointmentEntityOld = appointmentRepository.getByIdAndDoctorId(
        appointmentChangeStatusDTO.getId(), doctorId);
    appointmentEntityOld.setStatus(appointmentChangeStatusDTO.getStatus());
    appointmentRepository.save(appointmentEntityOld);
    return mapper.map(appointmentEntityOld, AppointmentDTO.class);
  }

  @Override
  public AppointmentDTO bookAppointmentByPatient(AppointmentRequest appointmentRequest) {
    UserEntity patient = userRepository.getReferenceById(appointmentRequest.getPatientId());
    UserEntity doctor = userRepository.getReferenceById(appointmentRequest.getDoctorId());
    TimeSlotEntity timeSlotEntity = timeSlotRepository.getReferenceById(
        appointmentRequest.getTimeSlotId());

    AppointmentEntity appointmentEntity = appointmentRepository.save(
        AppointmentEntity.builder().patient(patient)
            .doctor(doctor).timeSlot(timeSlotEntity).build());

    return mapper.map(appointmentEntity, AppointmentDTO.class);
  }
}
