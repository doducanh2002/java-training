package org.aibles.framework.error_handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aibles.framework.helper.I18nHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationErrorHandle implements AuthenticationEntryPoint {

  private final I18nHelper i18nHelper;

  public AuthenticationErrorHandle(I18nHelper i18nHelper) {
    this.i18nHelper = i18nHelper;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    log.info("(commence) message: {}", authException.getMessage());

    Locale locale = request.getLocale();
    String msgCode = authException.getMessage();
    SecurityException securityException = SecurityException.resolve(msgCode);
    if (securityException == null) {
      msgCode = SecurityException.AUTHENTICATION_EXCEPTION.getMsgCode();
    }
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");

    Map<String, Object> error = new HashMap<>();
    error.put("status", HttpServletResponse.SC_UNAUTHORIZED);
    error.put("timestamp", Instant.now().getEpochSecond());
    error.put("data", SecurityErrorResponse.builder()
        .message(i18nHelper.translate(msgCode, locale))
        .build());

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonResponse = objectMapper.writeValueAsString(error);

    response.getWriter().write(jsonResponse);
  }
}