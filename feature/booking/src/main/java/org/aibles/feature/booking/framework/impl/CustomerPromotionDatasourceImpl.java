package org.aibles.feature.booking.framework.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.framework.datasource.CustomerPromotionDatabaseEntity;
import org.aibles.feature.booking.framework.repository.CustomerPromotionDatabaseEntityRepository;
import org.aibles.feature.booking.util.CustomerPromotionMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerPromotionDatasourceImpl implements CustomerPromotionRepository {

  private final CustomerPromotionDatabaseEntityRepository customerPromotionDatabaseEntityRepository;

  @Override
  public void registerPromotion(CustomerPromotion customerPromotion) {
    customerPromotionDatabaseEntityRepository.save(
        CustomerPromotionMapping.convertEntityToDatabase(customerPromotion));
  }

  @Override
  public Optional<CustomerPromotion> findByCustomerIdAndPromotionId(String customerId, String promotionId) {
    Optional<CustomerPromotionDatabaseEntity> customerPromotionDatabase = customerPromotionDatabaseEntityRepository.findByCustomerIdAndPromotionId(
        customerId, promotionId);
    return customerPromotionDatabase.map(CustomerPromotionMapping::convertDatabaseToEntity);
  }

  @Override
  public Optional<CustomerPromotion> findByPromotionId(String promotionId) {
    Optional<CustomerPromotionDatabaseEntity> customerPromotionDatabase = customerPromotionDatabaseEntityRepository.findByPromotionId(promotionId);
    return customerPromotionDatabase.map(CustomerPromotionMapping::convertDatabaseToEntity);
  }

  @Override
  public List<CustomerPromotion> findAllCustomerByPromotionIdAndWinnerStatus(String promotionId, boolean winnerStatus) {
    List<CustomerPromotionDatabaseEntity> customerPromotionList = customerPromotionDatabaseEntityRepository.findAllByPromotionIdAndIsWinner(promotionId, winnerStatus);
    return CustomerPromotionMapping.convertDatabaseToEntityList(customerPromotionList);
  }

  @Override
  public void saveListCustomerPromotion(List<CustomerPromotion> listCustomerPromotion) {
    List<CustomerPromotionDatabaseEntity> customerPromotionList = CustomerPromotionMapping.convertDatabaseToDatabaseList(listCustomerPromotion);
    customerPromotionDatabaseEntityRepository.saveAll(customerPromotionList);
  }
}
