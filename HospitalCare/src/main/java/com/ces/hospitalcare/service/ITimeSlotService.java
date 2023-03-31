package com.ces.hospitalcare.service;
import com.ces.hospitalcare.http.response.TimeSlotResponse;
import java.util.List;

public interface ITimeSlotService {
  List<TimeSlotResponse> getAllTimeSlotByExaminationId(Long examinationId);

  TimeSlotResponse getDetailTimeSlot(Long id);

  List<TimeSlotResponse> getAllTimeSlotsOfCurrentWeek(Long doctorId);
}
