package org.aibles.feature.booking.business.usecase.impl;

import static org.aibles.feature.booking.constant.PromotionConstant.PROMOTION_GIVEAWAY;

import java.time.LocalDateTime;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.exception.PromotionExpiredLuckyDrawException;
import org.aibles.feature.booking.business.exception.PromotionNotFoundException;
import org.aibles.feature.booking.business.usecase.DeactivePromotionUseCase;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.quartz.service.QuartzService;
import org.quartz.SchedulerException;

public class DeactivePromotionUseCaseImpl implements DeactivePromotionUseCase {

  private final PromotionRepository promotionRepository;
  private final QuartzService quartzService;

  public DeactivePromotionUseCaseImpl(PromotionRepository promotionRepository, QuartzService quartzService) {
    this.promotionRepository = promotionRepository;
    this.quartzService = quartzService;
  }

  @Override
  public void invoke(String promotionId) throws SchedulerException {

    Promotion promotion = promotionRepository
        .findById(promotionId)
        .orElseThrow(() -> {
          return new PromotionNotFoundException(promotionId);
        });

    if (LocalDateTime.now().isAfter(promotion.getGiveAwayDay())) {
      throw new PromotionExpiredLuckyDrawException();
    }

    quartzService.pauseJob(PROMOTION_GIVEAWAY, promotionId);
  }
}
