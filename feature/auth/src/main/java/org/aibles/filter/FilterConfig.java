package org.aibles.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FilterConfig {

    @Value("${spring.key.decrypt}")
    private String decryptKey;

    @Bean
    public FilterRegistrationBean<AESRequestFilter> requestFilter() {
        log.info("Registering AESRequestFilter with key: {}", decryptKey);
        FilterRegistrationBean<AESRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AESRequestFilter(decryptKey));
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }
}
