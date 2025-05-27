package org.aibles.aop.transaction;

import org.aibles.business.BaseException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Aspect
@Component
public class AOPTransactionConfig {

  private final TransactionTemplate transactionTemplate;

  public AOPTransactionConfig(TransactionTemplate transactionTemplate) {
    this.transactionTemplate = transactionTemplate;
  }

  @Pointcut(value = """
       execution(* org.aibles.business.usecase.impl..invoke(..))
       || execution(* org.aibles.feature.booking.business.usecase.impl..invoke(..))
       || execution(* org.aibles.feature.customer.business.usecase.impl..invoke(..))
      """)
  public void useCasePointcut() {
  }

  @Around(value = "useCasePointcut()")
  public Object addTransaction(ProceedingJoinPoint joinPoint) {
    return transactionTemplate.execute(ts -> this.executeTransaction(joinPoint));
  }

  private Object executeTransaction(ProceedingJoinPoint joinPoint) {
    Object returnObject;
    try {
      returnObject = joinPoint.proceed();
    } catch (BaseException ex) {
      throw ex;
    } catch (Throwable ex) {
      throw new AopInvocationException(ex.getMessage());
    }
    return returnObject;
  }
}
