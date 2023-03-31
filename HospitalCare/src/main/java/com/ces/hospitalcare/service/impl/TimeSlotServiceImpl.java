package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.TimeSlotDTO;
import com.ces.hospitalcare.entity.TimeSlotEntity;
import com.ces.hospitalcare.http.response.TimeSlotResponse;
import com.ces.hospitalcare.repository.TimeSlotRepository;
import com.ces.hospitalcare.service.ITimeSlotService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImpl implements ITimeSlotService {
  @Autowired
  private TimeSlotRepository timeSlotRepository;

  @Autowired
  private ModelMapper mapper;

  public List<TimeSlotResponse> createListTimeSlotResponse(
      List<TimeSlotEntity> listTimeSlotEntity) {
    List<TimeSlotResponse> listTimeSlotResponse = new ArrayList<>();

    for (TimeSlotEntity entity : listTimeSlotEntity) {

      TimeSlotDTO dto = mapper.map(entity, TimeSlotDTO.class);
      TimeSlotResponse timeSlotResponse = TimeSlotResponse.builder().timeSlotDTO(dto)
          .doctorId(entity.getDoctor().getId()).build();
      listTimeSlotResponse.add(timeSlotResponse);
    }

    return listTimeSlotResponse;
  }

  @Override
  public List<TimeSlotResponse> getAllTimeSlotByExaminationId(Long examinationId) {
    List<TimeSlotResponse> listTimeSlotResponse = new ArrayList<>();
    List<TimeSlotEntity> listTimeSlotEntity = timeSlotRepository.getAllByMedicalExaminationId(
        examinationId);

    return createListTimeSlotResponse(listTimeSlotEntity);
  }

  @Override
  public TimeSlotResponse getDetailTimeSlot(Long id) {
    TimeSlotEntity timeSlotEntity = timeSlotRepository.getById(id);
    TimeSlotDTO timeSlotDTO = mapper.map(timeSlotEntity, TimeSlotDTO.class);
    TimeSlotResponse timeSlotResponse = TimeSlotResponse.builder().timeSlotDTO(timeSlotDTO)
        .doctorId(timeSlotEntity.getDoctor().getId()).build();
    return timeSlotResponse;
  }

  @Override
  public List<TimeSlotResponse> getAllTimeSlotsOfCurrentWeek(Long doctorId) {
    List<TimeSlotEntity> listTimeSlotEntity = timeSlotRepository.getAllTimeSlotsOfCurrentWeek(
        doctorId);
    return createListTimeSlotResponse(listTimeSlotEntity);
  }
}
