package com.ces.hospitalcare.dto;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeSlotDTO extends BaseDTO {
  private Date startTime;

  private Integer duration;

  private MedicalExaminationDTO medicalExamination;
}
