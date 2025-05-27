package org.aibles.feature.booking.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.framework.repository.projection.RegisterPromotionProjection;

public interface PromotionRepository {

  PagingResponse<Promotion> getValidPromotion(PagingRequest request);

  List<Promotion> importPromotions(List<Promotion> promotions);

  Optional<RegisterPromotionProjection> findArchivedMilesAndGiveAwayDayById(String promotionId);

  Optional<Promotion> findById(String id);

  Optional<Promotion> findByCode(String code);

  void savePromotion(Promotion promotion);

  List<Promotion> findAllPromotions();

  void deleteZeroQuantityPromotion();
}
