package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.DepartmentDTO;
import com.ces.hospitalcare.entity.DepartmentEntity;
import com.ces.hospitalcare.entity.MedicalExaminationEntity;
import com.ces.hospitalcare.http.exception.DeleteDepartmentException;
import com.ces.hospitalcare.repository.DepartmentRepository;
import com.ces.hospitalcare.repository.MedicalExaminationRepository;
import com.ces.hospitalcare.service.IDepartmentService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private MedicalExaminationRepository medicalExaminationRepository;

  public List<DepartmentDTO> createListDepartmentDTO(List<DepartmentEntity> departmentEntityList) {

    List<DepartmentDTO> departmentDTOList = new ArrayList<>();

    for (DepartmentEntity entity : departmentEntityList) {
      DepartmentDTO dto = mapper.map(entity, DepartmentDTO.class);

      departmentDTOList.add(dto);
    }

    return departmentDTOList;
  }

  @Override
  public List<DepartmentDTO> getAllDepartment() {

    return createListDepartmentDTO(departmentRepository.findAll());
  }

  @Override
  public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
    DepartmentEntity departmentEntity = mapper.map(departmentDTO, DepartmentEntity.class);
    departmentRepository.save(departmentEntity);

    return mapper.map(departmentEntity, DepartmentDTO.class);
  }

  @Override
  public DepartmentDTO editDepartment(DepartmentDTO departmentDTO) {
    DepartmentEntity departmentEntity = departmentRepository.getReferenceById(
        departmentDTO.getId());

    departmentEntity.setName(departmentDTO.getName());
    departmentRepository.save(departmentEntity);

    return mapper.map(departmentEntity, DepartmentDTO.class);
  }

  @Override
  public String deleteDepartment(Long departmentId) {
    List<MedicalExaminationEntity> medicalExaminationEntityList = medicalExaminationRepository.getAllByDepartmentId(
        departmentId);

    if (medicalExaminationEntityList.size() != 0) {
      throw new DeleteDepartmentException("You can't delete");
    }

    departmentRepository.delete(departmentRepository.getReferenceById(departmentId));
    return "Delete successfully";
  }
}
