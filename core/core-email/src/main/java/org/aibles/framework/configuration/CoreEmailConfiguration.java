package org.aibles.framework.configuration;

import org.aibles.adapter.repository.EmailHelper;
import org.aibles.framework.repository.datasource.EmailHelperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class CoreEmailConfiguration {

  @Bean
  public EmailHelper emailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
    return new EmailHelperImpl(emailSender, templateEngine);
  }

}
