package org.aibles.feature.booking.business.usecase;

import org.quartz.SchedulerException;

public interface DeactivePromotionUseCase {

  void invoke(String promotionId) throws SchedulerException;
}
