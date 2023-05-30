package com.ces.hospitalcare.service;
import jakarta.mail.MessagingException;
import java.util.Date;

public interface IEmailService {
  void sendEmail(String toEmail, String subject, String body) throws MessagingException;
  String messageReminderBody(String doctorName, String patientName, Date appointmentDate);
  String messageDoctorCancelAppointmentBody(String doctorName, String patientName, Date appointmentDate);
  String messageDoctorAcceptAppointmentBody(String doctorName, String patientName, Date appointmentDate);
  String messageSubject(String doctorName, String titleSubject);
}
