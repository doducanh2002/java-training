package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.service.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {


  @Autowired
  private JavaMailSender emailSender;

  @Override
  public void send(String subject, String to, String content) {
    log.info("(send)subject: {}, to: {}, content: {}", subject, to, content);
    try {
      var message = new SimpleMailMessage();
      message.setTo(to);
      message.setSubject(subject);
      message.setText(content);
      emailSender.send(message);
    } catch (Exception ex) {
      log.error("(send)subject: {}, to: {}, ex: {}", subject, to, ex.getMessage());
      // Xử lý ngoại lệ khi gửi email không thành công
    }
  }

  @Override
  public String generateOTP() {
    // Generate a random 4-digit OTP
    return RandomStringUtils.randomNumeric(4);
  }

  @Override
  public void sendOTP(String email, String otp) {
    String subject = "Your OTP";
    String body = "Your OTP is: " + otp;
    send(subject, email, body);
  }

}
