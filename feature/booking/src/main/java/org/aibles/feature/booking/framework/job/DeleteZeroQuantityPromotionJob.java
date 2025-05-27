package org.aibles.feature.booking.framework.job;

import lombok.RequiredArgsConstructor;
import org.aibles.feature.booking.business.usecase.DeleteZeroQuantityPromotionUsecase;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
@DisallowConcurrentExecution
public class DeleteZeroQuantityPromotionJob implements Job {

  private final DeleteZeroQuantityPromotionUsecase deleteZeroQuantityPromotionUsecase;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    deleteZeroQuantityPromotionUsecase.invoke();
  }
}
