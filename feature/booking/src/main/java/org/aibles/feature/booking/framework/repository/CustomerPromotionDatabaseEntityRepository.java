package org.aibles.feature.booking.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.feature.booking.framework.datasource.CustomerPromotionDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPromotionDatabaseEntityRepository extends
    JpaRepository<CustomerPromotionDatabaseEntity, String> {

  @Query("SELECT cp FROM CustomerPromotionDatabaseEntity cp WHERE cp.customerId = :customerId AND cp.promotionId = :promotionId")
  Optional<CustomerPromotionDatabaseEntity> findByCustomerIdAndPromotionId(String customerId, String promotionId);

  Optional<CustomerPromotionDatabaseEntity> findByPromotionId(String promotionId);

  List<CustomerPromotionDatabaseEntity> findAllByPromotionIdAndIsWinner(String promotionId, boolean winnerStatus);
}
