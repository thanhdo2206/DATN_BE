package com.ces.hospitalcare.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Appointments")
public class AppointmentEntity extends BaseEntity {
  @Column
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "patientId")
  private UserEntity patient;

  @ManyToOne
  @JoinColumn(name = "doctorId")
  private UserEntity doctor;

  @OneToOne
  @JoinColumn(name = "timeSlotId")
  private TimeSlotEntity timeSlot;
}
