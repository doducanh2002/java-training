package org.aibles.framework.configuration;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.i18n")
@Data
public class MessageResourcesProperties {
  private List<String> resources =  new ArrayList<>();
}
