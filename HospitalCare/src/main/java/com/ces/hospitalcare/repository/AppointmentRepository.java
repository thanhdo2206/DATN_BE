package com.ces.hospitalcare.repository;
import com.ces.hospitalcare.entity.AppointmentEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
  List<AppointmentEntity> getAllByPatientId(Long patientId);

  @Query(value = "SELECT appointments.* FROM appointments\n"
      + "inner join time_slots\n"
      + "on time_slots.id = appointments.time_slot_id\n"
      + "WHERE appointments.doctor_id = :doctorId and YEARWEEK(time_slots.start_time) = YEARWEEK(NOW());", nativeQuery = true)
  List<AppointmentEntity> getAllAppointmentOfDoctorCurrentWeek(@Param("doctorId") Long doctorId);

  List<AppointmentEntity> getAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

  AppointmentEntity getByIdAndDoctorId(Long id, Long doctorId);

  List<AppointmentEntity> findAllByStatusAndDoctorId(int status, Long doctorId, Pageable pageable);

  int countByStatusAndDoctorId(int status, Long doctorId);
}
