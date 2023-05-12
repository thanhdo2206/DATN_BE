package com.ces.hospitalcare.service;
import com.ces.hospitalcare.dto.DepartmentDTO;
import java.util.List;

public interface IDepartmentService {
  List<DepartmentDTO> getAllDepartment();

  DepartmentDTO addDepartment(DepartmentDTO departmentDTO);

  DepartmentDTO editDepartment(DepartmentDTO departmentDTO);

  String deleteDepartment(Long departmentId);
}
