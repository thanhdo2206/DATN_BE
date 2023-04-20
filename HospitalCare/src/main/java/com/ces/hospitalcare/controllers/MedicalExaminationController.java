package com.ces.hospitalcare.controllers;
import com.ces.hospitalcare.http.response.MedicalExaminationResponse;
import com.ces.hospitalcare.service.IMedicalExaminationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/guest/medical_examinations")
public class MedicalExaminationController {
  @Autowired
  private IMedicalExaminationService medicalExaminationService;

  @GetMapping(path = "")
  public List<MedicalExaminationResponse> getAllMedicalExamination() {

    return medicalExaminationService.getAllMedicalExamination();
  }

  @GetMapping(value = "/{id}")
  public MedicalExaminationResponse getDetailMedicalExamination(
      @PathVariable("id") Long medicalExaminationId) {
    return medicalExaminationService.getDetailMedicalExamination(medicalExaminationId);
  }

  @GetMapping(value = "/filter")
  public List<MedicalExaminationResponse> filterMedicalExaminationByCategoryAndPrice(
      @RequestParam(value = "category[]") String[] categories,
      @RequestParam(value = "minPrice", required = false) Long minPrice,
      @RequestParam(value = "maxPrice", required = false) Long maxPrice) {

    return medicalExaminationService.filterMedicalExaminationByCategoryAndPrice(
        minPrice, maxPrice, categories);
  }
}
