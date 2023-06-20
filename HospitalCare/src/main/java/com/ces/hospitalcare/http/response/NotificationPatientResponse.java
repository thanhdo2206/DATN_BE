package com.ces.hospitalcare.http.response;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationPatientResponse {
  private Date createdDate;

  private Boolean isRead;

  private AppointmentResponse inforNotification;
}
