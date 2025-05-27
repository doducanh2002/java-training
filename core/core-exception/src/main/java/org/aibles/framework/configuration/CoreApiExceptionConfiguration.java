package org.aibles.framework.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aibles.framework.constant.ExceptionApiConstant;
import org.aibles.framework.helper.I18nHelper;
import org.aibles.framework.helper.I18nHelperImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = {"org.aibles.framework"})
@Slf4j
public class CoreApiExceptionConfiguration {

  @Bean
  public GlobalExceptionHandler apiExceptionHandler(I18nHelper i18nHelper) {
    return new GlobalExceptionHandler(i18nHelper);
  }

  @Bean
  public MessageSource messageSource(MessageResourcesProperties resource) {
    log.info("(messageSource)resource: {}", resource.getResources());
    var messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames(resource.getResources().toArray(String[]::new));
    messageSource.setDefaultEncoding(ExceptionApiConstant.UTF_8_ENCODING);
    return messageSource;
  }

  @Bean
  public I18nHelper i18nHelper(MessageSource messageSource) {
    return new I18nHelperImpl(messageSource);
  }
}
