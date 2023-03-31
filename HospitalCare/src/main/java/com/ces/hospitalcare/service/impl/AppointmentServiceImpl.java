package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.AppointmentDTO;
import com.ces.hospitalcare.entity.AppointmentEntity;
import com.ces.hospitalcare.repository.AppointmentRepository;
import com.ces.hospitalcare.service.IAppointmentService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
  @Autowired
  private AppointmentRepository appointmentRepository;

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
    AppointmentEntity appointmentEntity = appointmentRepository.getByIdAndDoctorId(
        appointmentChangeStatusDTO.getId(), doctorId);
    appointmentEntity.setStatus(appointmentChangeStatusDTO.getStatus());
    appointmentRepository.save(appointmentEntity);
    return mapper.map(appointmentEntity, AppointmentDTO.class);
  }
}
