package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.bussiness.dto.ImportPromotionDTO;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.framework.datasource.PromotionDatabaseEntity;
import org.springframework.data.domain.Page;

public class PromotionMapping {

  public static Promotion convertDatabaseToEntity(PromotionDatabaseEntity databaseEntity) {
    Promotion promotion = new Promotion();
    promotion.setId(databaseEntity.getId());
    promotion.setCode(databaseEntity.getCode());
    promotion.setDescription(databaseEntity.getDescription());
    promotion.setExpiredDate(databaseEntity.getExpiredDate());
    promotion.setDiscountPercent(databaseEntity.getDiscountPercent());
    promotion.setArchivedMiles(databaseEntity.getArchivedMiles());
    promotion.setGiveAwayDay(databaseEntity.getGiveAwayDay());
    promotion.setPlusMiles(databaseEntity.getPlusMiles());
    promotion.setQuantity(databaseEntity.getQuantity());
    return promotion;
  }

  public static PromotionDatabaseEntity convertEntityToDatabase(Promotion promotion) {
    PromotionDatabaseEntity databaseEntity = new PromotionDatabaseEntity();
    databaseEntity.setId(promotion.getId());
    databaseEntity.setCode(promotion.getCode());
    databaseEntity.setDescription(promotion.getDescription());
    databaseEntity.setExpiredDate(promotion.getExpiredDate());
    databaseEntity.setDiscountPercent(promotion.getDiscountPercent());
    databaseEntity.setArchivedMiles(promotion.getArchivedMiles());
    databaseEntity.setGiveAwayDay(promotion.getGiveAwayDay());
    databaseEntity.setPlusMiles(promotion.getPlusMiles());
    databaseEntity.setQuantity(promotion.getQuantity());
    return databaseEntity;
  }

  public static List<Promotion> convertDatabaseToEntity(
      List<PromotionDatabaseEntity> databaseEntityList) {
    List<Promotion> promotions = new ArrayList<Promotion>();
    for (PromotionDatabaseEntity databaseEntity : databaseEntityList) {
      Promotion promotion = convertDatabaseToEntity(databaseEntity);
      promotions.add(promotion);
    }
    return promotions;
  }

  public static Promotion convertIdToPromotion(String promotionId) {
    Promotion promotion = new Promotion();
    promotion.setId(promotionId);
    return promotion;
  }

  public static Promotion convertDTOtoEntity(ImportPromotionDTO promotionDTO) {
    Promotion promotion = new Promotion();
    promotion.setCode(promotionDTO.getCode());
    promotion.setDescription(promotionDTO.getDescription());
    promotion.setExpiredDate(promotionDTO.getExpiredDate());
    promotion.setDiscountPercent(promotionDTO.getDiscountPercent());
    promotion.setArchivedMiles(promotionDTO.getArchivedMiles());
    promotion.setGiveAwayDay(promotionDTO.getGiveAwayDay());
    promotion.setPlusMiles(promotionDTO.getPlusMiles());
    promotion.setQuantity(promotionDTO.getQuantity());
    return promotion;
  }

  public static List<Promotion> convertListDTOtoListEntity(
      List<ImportPromotionDTO> importPromotionDTOList) {
    return importPromotionDTOList.stream().map(PromotionMapping::convertDTOtoEntity).toList();
  }

  public static PagingResponse<Promotion> convertDatabaseToPagingEntity(
      Page<PromotionDatabaseEntity> databaseEntityPage) {
    List<Promotion> promotionList = databaseEntityPage.getContent().stream()
        .map(PromotionMapping::convertDatabaseToEntity).toList();
    return new PagingResponse<>(databaseEntityPage.getNumber(), databaseEntityPage.getSize(),
        databaseEntityPage.getTotalElements(), promotionList);
  }
}
