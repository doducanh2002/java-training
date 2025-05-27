package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.usecase.DeleteZeroQuantityPromotionUsecase;

public class DeleteZeroQuantityPromotionUsecaseImpl implements DeleteZeroQuantityPromotionUsecase {
  private final PromotionRepository promotionRepository;

  public DeleteZeroQuantityPromotionUsecaseImpl(PromotionRepository promotionRepository) {
    this.promotionRepository = promotionRepository;
  }

  @Override
  public void invoke() {
    promotionRepository.deleteZeroQuantityPromotion();
  }
}
