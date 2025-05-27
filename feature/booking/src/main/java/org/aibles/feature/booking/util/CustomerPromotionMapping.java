package org.aibles.feature.booking.util;

import java.util.ArrayList;
import java.util.List;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.aibles.feature.booking.framework.datasource.CustomerPromotionDatabaseEntity;

public class CustomerPromotionMapping {

  public static CustomerPromotionDatabaseEntity convertEntityToDatabase(
      CustomerPromotion customerPromotion) {
    CustomerPromotionDatabaseEntity databaseEntity = new CustomerPromotionDatabaseEntity();
    databaseEntity.setId(customerPromotion.getId());
    databaseEntity.setCustomerId(customerPromotion.getCustomer().getId());
    databaseEntity.setPromotionId(customerPromotion.getPromotion().getId());
    databaseEntity.setWinner(customerPromotion.getWinner());
    databaseEntity.setIsUsed(customerPromotion.getIsUsed());
    return databaseEntity;
  }

  public static CustomerPromotion convertDatabaseToEntity(
      CustomerPromotionDatabaseEntity databaseEntity) {
    CustomerPromotion customerPromotion = new CustomerPromotion();
    customerPromotion.setId(databaseEntity.getId());
    Customer customer = new Customer();
    customer.setId(databaseEntity.getCustomerId());
    customerPromotion.setCustomer(customer);
    Promotion promotion = new Promotion();
    promotion.setId(databaseEntity.getPromotionId());
    customerPromotion.setPromotion(promotion);
    customerPromotion.setWinner(databaseEntity.getWinner());
    customerPromotion.setIsUsed(databaseEntity.getIsUsed());
    return customerPromotion;
  }

  public static List<CustomerPromotion> convertDatabaseToEntityList(List<CustomerPromotionDatabaseEntity> promotionDatabases) {
    if (promotionDatabases == null) {
      return new ArrayList<>();
    }
    return promotionDatabases.stream().map(CustomerPromotionMapping::convertDatabaseToEntity).toList();
  }

  public static List<CustomerPromotionDatabaseEntity> convertDatabaseToDatabaseList(List<CustomerPromotion> customerPromotions) {
    if (customerPromotions == null) {
      return new ArrayList<>();
    }
    return customerPromotions.stream().map(CustomerPromotionMapping::convertEntityToDatabase).toList();
  }
}
