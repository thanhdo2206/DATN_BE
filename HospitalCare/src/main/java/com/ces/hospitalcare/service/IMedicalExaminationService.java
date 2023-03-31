package com.ces.hospitalcare.service;
import com.ces.hospitalcare.http.response.MedicalExaminationResponse;
import java.util.List;

public interface IMedicalExaminationService {
  List<MedicalExaminationResponse> getAllMedicalExamination();

  MedicalExaminationResponse getDetailMedicalExamination(Long id);

  List<MedicalExaminationResponse> filterMedicalExaminationByCategoryAndPrice(
      Long startPrice, Long endPrice, String[] categories);
}
