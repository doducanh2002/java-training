package org.aibles.feature.booking.framework.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.framework.datasource.PromotionDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.PromotionProjection;
import org.aibles.feature.booking.framework.repository.PromotionDatabaseEntityRepository;
import org.aibles.feature.booking.framework.repository.projection.RegisterPromotionProjection;
import org.aibles.feature.booking.util.PromotionMapping;
import org.aibles.feature.booking.util.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PromotionDatasourceImpl implements PromotionRepository {

  private final PromotionDatabaseEntityRepository promotionRepository;

  @Override
  @Transactional
  public PagingResponse<Promotion> getValidPromotion(PagingRequest pagingReq)  {
    Pageable pageable = SortUtil.makePageable(
        pagingReq.getPage(),
        pagingReq.getSize(),
        pagingReq.getSort(),
        PromotionDatabaseEntity.class
    );
    Page<PromotionDatabaseEntity> promotionPage = promotionRepository.findAll(pageable);

    return PromotionMapping.convertDatabaseToPagingEntity(promotionPage);

  }

  @Override
  public List<Promotion> importPromotions(List<Promotion> promotions) {
    List<PromotionDatabaseEntity> promotionDatabaseEntityList = promotions.stream().map(
        PromotionMapping::convertEntityToDatabase).toList();
    List<PromotionProjection> existPromotions = promotionRepository.findAllPromotionIdByCode(promotions.stream().map(
        Promotion::getCode).toList());
    Map<String, String> existPromotionCodeMap =  existPromotions.stream()
        .collect(Collectors.toMap(PromotionProjection::getCode, PromotionProjection::getId));
    promotionDatabaseEntityList = promotionDatabaseEntityList.stream().map(p -> {
      if (existPromotionCodeMap.containsKey(p.getCode())) {
        p.setId(existPromotionCodeMap.get(p.getCode()));
        return p;
      }
      return p;
    }).toList();
    List<PromotionDatabaseEntity> savedList = promotionRepository.saveAll(promotionDatabaseEntityList);
    return PromotionMapping.convertDatabaseToEntity(savedList);
  }

  @Override
  public Optional<RegisterPromotionProjection> findArchivedMilesAndGiveAwayDayById(String promotionId) {
    return promotionRepository.findArchivedMilesAndGiveAwayDayById(promotionId);
  }

  @Override
  public Optional<Promotion> findById(String id) {
    Optional<PromotionDatabaseEntity> promotionDatabaseEntity = promotionRepository.findById(id);
    return promotionDatabaseEntity.map(PromotionMapping::convertDatabaseToEntity);
  }

  @Override
  public Optional<Promotion> findByCode(String code) {
    Optional<PromotionDatabaseEntity> promotionDatabaseEntity = promotionRepository.findByCode(code);
    return promotionDatabaseEntity.map(PromotionMapping::convertDatabaseToEntity);
  }

  @Override
  public void savePromotion(Promotion promotion) {
    promotionRepository.save(PromotionMapping.convertEntityToDatabase(promotion));
  }

  @Override
  public List<Promotion> findAllPromotions() {
    List<PromotionDatabaseEntity> promotionDatabaseEntityList = promotionRepository.findAll();
    if (promotionDatabaseEntityList.isEmpty()) {
      return new ArrayList<>();
    }
    return PromotionMapping.convertDatabaseToEntity(promotionDatabaseEntityList);
  }

  @Override
  public void deleteZeroQuantityPromotion() {
    promotionRepository.deleteZeroQuantityPromotion();
  }
}
