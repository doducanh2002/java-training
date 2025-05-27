package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportPromotionDTO;
import org.aibles.feature.booking.adapter.repository.MapHelper;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.adapter.repository.RedisPublisher;
import org.aibles.feature.booking.business.usecase.ImportPromotionUseCase;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.util.PromotionMapping;

@Slf4j
public class ImportPromotionUseCaseImpl implements ImportPromotionUseCase {

  private final ExcelHelper excelHelper;
  private final PromotionRepository promotionRepository;
  private final RedisPublisher redisPublisher;
  private final MapHelper mapHelper;

  public ImportPromotionUseCaseImpl(ExcelHelper excelHelper, PromotionRepository promotionRepository, RedisPublisher redisPublisher, MapHelper mapHelper) {
    this.excelHelper = excelHelper;
    this.promotionRepository = promotionRepository;
    this.redisPublisher = redisPublisher;
    this.mapHelper = mapHelper;
  }

  @Override
  public void invoke(InputStream inputStream)  {
    List<ImportPromotionDTO> importPromotionDTOList = excelHelper.importPromotion(inputStream);
    try {
      List<Promotion> savedList = promotionRepository.importPromotions(PromotionMapping.convertListDTOtoListEntity(importPromotionDTOList));
      redisPublisher.publishPromotionImportedEvent(mapHelper.mapListPromotionToString(savedList));
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
