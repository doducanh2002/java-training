package org.aibles.quartz.configuration;

import org.aibles.quartz.service.QuartzService;
import org.aibles.quartz.service.QuartzServiceImpl;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreQuartzConfiguration {

  @Bean
  public QuartzService quartzService(Scheduler scheduler) {
    return new QuartzServiceImpl(scheduler);
  }
}
