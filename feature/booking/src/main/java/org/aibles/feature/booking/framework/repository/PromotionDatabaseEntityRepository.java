package org.aibles.feature.booking.framework.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.PromotionDatabaseEntity;
import org.aibles.feature.booking.framework.datasource.PromotionProjection;
import org.aibles.feature.booking.framework.repository.projection.RegisterPromotionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDatabaseEntityRepository extends
    JpaRepository<PromotionDatabaseEntity, String> {

  @Query(value = "SELECT * FROM promotion WHERE expired_date > NOW() ORDER BY expired_date DESC", nativeQuery = true)
  List<PromotionDatabaseEntity> findAllValidPromotions();

  @Query("SELECT p.id AS id, p.code AS code FROM PromotionDatabaseEntity p WHERE p.code IN :codes")
  List<PromotionProjection> findAllPromotionIdByCode(List<String> codes);

  @Query("SELECT new org.aibles.feature.booking.framework.repository.projection.RegisterPromotionProjection(p.archivedMiles, p.giveAwayDay)"
      + " FROM PromotionDatabaseEntity p WHERE p.id = :promotionId")
  Optional<RegisterPromotionProjection> findArchivedMilesAndGiveAwayDayById(String promotionId);

  Optional<PromotionDatabaseEntity> findByCode(String code);

  @Modifying
  @Transactional
  @Query("DELETE FROM PromotionDatabaseEntity WHERE quantity = 0")
  void deleteZeroQuantityPromotion();
}