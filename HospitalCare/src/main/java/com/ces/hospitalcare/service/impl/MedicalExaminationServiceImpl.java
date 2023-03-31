package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.MedicalExaminationDTO;
import com.ces.hospitalcare.entity.MedicalExaminationEntity;
import com.ces.hospitalcare.http.response.MedicalExaminationResponse;
import com.ces.hospitalcare.http.response.TimeSlotResponse;
import com.ces.hospitalcare.repository.MedicalExaminationRepository;
import com.ces.hospitalcare.service.IMedicalExaminationService;
import com.ces.hospitalcare.service.ITimeSlotService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalExaminationServiceImpl implements IMedicalExaminationService {
  @Autowired
  private MedicalExaminationRepository medicalExaminationRepository;

  @Autowired
  private ITimeSlotService timeSlotService;

  @Autowired
  private ModelMapper mapper;

  public List<MedicalExaminationResponse> createListMedicalExaminationResponse(
      List<MedicalExaminationEntity> listMedicalExaminationEntity) {
    List<MedicalExaminationResponse> listMedicalExaminationResponse = new ArrayList<>();

    for (MedicalExaminationEntity entity : listMedicalExaminationEntity) {
      MedicalExaminationDTO dto = mapper.map(entity, MedicalExaminationDTO.class);

      List<TimeSlotResponse> listTimeSlotDTO =
          timeSlotService.getAllTimeSlotByExaminationId(entity.getId());

      MedicalExaminationResponse medicalExaminationResponse = MedicalExaminationResponse.builder()
          .medicalExamination(dto).listTimeSlot(listTimeSlotDTO)
          .build();
      listMedicalExaminationResponse.add(medicalExaminationResponse);
    }

    return listMedicalExaminationResponse;
  }

  @Override
  public List<MedicalExaminationResponse> getAllMedicalExamination() {
    List<MedicalExaminationEntity> listMedicalExaminationEntity = medicalExaminationRepository.findAll();

    return createListMedicalExaminationResponse(listMedicalExaminationEntity);
  }

  @Override
  public MedicalExaminationResponse getDetailMedicalExamination(Long id) {

    MedicalExaminationEntity medicalExaminationEntity = medicalExaminationRepository.getById(
        id);
    MedicalExaminationDTO dto = mapper.map(medicalExaminationEntity, MedicalExaminationDTO.class);

    List<TimeSlotResponse> listTimeSlotDTO =
        timeSlotService.getAllTimeSlotByExaminationId(medicalExaminationEntity.getId());

    return MedicalExaminationResponse.builder()
        .medicalExamination(dto).listTimeSlot(listTimeSlotDTO)
        .build();
  }

  public List<MedicalExaminationEntity> filterMedicalExaminationEntity(
      Long minPrice, Long maxPrice, String[] categories) {

    if (minPrice == null && maxPrice == null) {
      List<MedicalExaminationEntity> listMedicalExaminationEntity =
          categories.length == 0 ? medicalExaminationRepository.findAll() :
              medicalExaminationRepository.filterMedicalExaminationByCategory(categories);

      return listMedicalExaminationEntity;
    }

    if (categories.length == 0) {

      return medicalExaminationRepository.filterMedicalExaminationByPrice(minPrice, maxPrice);
    }

    return medicalExaminationRepository.filterMedicalExaminationByCategoryAndPrice(
        minPrice, maxPrice, categories);
  }

  @Override
  public List<MedicalExaminationResponse> filterMedicalExaminationByCategoryAndPrice(
      Long minPrice, Long maxPrice, String[] categories) {

    List<MedicalExaminationEntity> listMedicalExaminationEntity =
        filterMedicalExaminationEntity(minPrice, maxPrice, categories);

    return createListMedicalExaminationResponse(listMedicalExaminationEntity);
  }
}
