package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.dto.DepartmentDTO;
import com.ces.hospitalcare.service.IDepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
  @Autowired
  private IDepartmentService departmentService;

  @GetMapping(value = "")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public List<DepartmentDTO> getAllDepartment() {
    return departmentService.getAllDepartment();
  }

  @PostMapping(value = "")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public DepartmentDTO addDepartment(@RequestBody DepartmentDTO departmentDTO) {
    return departmentService.addDepartment(departmentDTO);
  }

  @PatchMapping(value = "/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public DepartmentDTO editDepartment(@RequestBody DepartmentDTO departmentDTO,
      @PathVariable("id") Long departmentId) {
    departmentDTO.setId(departmentId);
    return departmentService.editDepartment(departmentDTO);
  }

  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
  public String deleteDepartment(@PathVariable("id") Long departmentId) {
    return departmentService.deleteDepartment(departmentId);
  }
}
