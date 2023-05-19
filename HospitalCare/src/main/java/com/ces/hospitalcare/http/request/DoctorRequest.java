package com.ces.hospitalcare.http.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
  private DoctorRegisterRequest doctor;

  private MedicalExaminationRequest medicalExamination;
}
