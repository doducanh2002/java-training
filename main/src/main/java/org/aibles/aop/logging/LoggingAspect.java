package org.aibles.aop.logging;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  private final Counter successCounter;
  private final Counter failureCounter;

  public LoggingAspect(MeterRegistry meterRegistry) {
    this.successCounter = Counter.builder("api.calls.success")
        .description("Number of successful API calls").register(meterRegistry);
    this.failureCounter = Counter.builder("api.calls.failure")
        .description("Number of failed API calls").register(meterRegistry);
  }

  @Before("""
  within(org.aibles..*) && !within(org.aibles.framework.filter.*)
  """)
  public void logBeforeMethod(JoinPoint joinPoint) {
    log.info("Entering method: {} with arguments: {}", joinPoint.getSignature().getName(),
        Arrays.toString(
            joinPoint.getArgs()));
  }

  @AfterReturning(pointcut = """
  within(org.aibles..*) && !within(org.aibles.framework.filter.*)
  """, returning = "result")
  public void logAfterMethod(JoinPoint joinPoint, Object result) {
    log.info("Exiting method: {} with result: {}", joinPoint.getSignature().getName(),
        result);
  }

  @AfterThrowing(pointcut = """
  within(org.aibles..*) && !within(org.aibles.framework.filter.*)
  """, throwing = "exception")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
    log.error("Exception in method: {} with cause : {}",
        joinPoint.getSignature().getName(), exception.getClass().getName());
  }

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void restControllerMethods() {
  }

  @AfterReturning(pointcut = "restControllerMethods()", returning = "result")
  public void afterSuccessfulApiCall(JoinPoint joinPoint, Object result) {
    log.info("Successfully returned from method: {} with result: {}",
        joinPoint.getSignature().getName(), result);
    successCounter.increment();
  }

  @AfterThrowing(pointcut = "restControllerMethods()", throwing = "exception")
  public void afterFailedApiCall(JoinPoint joinPoint, Throwable exception) {
    log.info("Exception thrown in method: {} with cause: {}", joinPoint.getSignature().getName(),
        exception.getCause());
    failureCounter.increment();
  }
}
