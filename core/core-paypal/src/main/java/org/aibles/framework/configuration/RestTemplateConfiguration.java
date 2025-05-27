package org.aibles.framework.configuration;

import org.aibles.framework.advice.RestTemplateErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  private final RestTemplateErrorHandler errorHandler;
  public RestTemplateConfiguration(RestTemplateErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Bean("paypalRestTemplate")
  public RestTemplate paypalRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(errorHandler);
    return restTemplate;
  }

  @Bean("restTemplate")
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(errorHandler);
    return restTemplate;
  }
}
