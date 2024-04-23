package org.aibles.privatetraining.service;

import java.util.Map;

public interface EmailService {
  /**
   * Send email with text
   * @param subject - the subject of the email
   * @param to - the email address of the receiver
   * @param content - the body of the email
   */
  void send(String subject, String to, String content);


  String generateOTP();

  void sendOTP(String email, String otp);
}
