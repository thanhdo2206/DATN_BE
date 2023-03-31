package com.ces.hospitalcare.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TimeSlots")
public class TimeSlotEntity extends BaseEntity {
  @Column
  private Date startTime;

  @Column
  private Integer duration;

  @ManyToOne
  @JoinColumn(name = "examinationId")
  private MedicalExaminationEntity medicalExamination;

  @ManyToOne
  @JoinColumn(name = "doctorId")
  private UserEntity doctor;

  @OneToOne(mappedBy = "timeSlot")
  private AppointmentEntity appointment;
}
