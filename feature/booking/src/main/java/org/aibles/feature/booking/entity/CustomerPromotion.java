package org.aibles.feature.booking.entity;

import org.aibles.entity.Customer;

public class CustomerPromotion {

  private String id;
  private Customer customer;
  private Promotion promotion;
  private Boolean isWinner;
  private Boolean isUsed;

  public CustomerPromotion() {
  }

  public CustomerPromotion(String id, Customer customer, Promotion promotion, Boolean isWinner,
      Boolean isUsed) {
    this.id = id;
    this.customer = customer;
    this.promotion = promotion;
    this.isWinner = isWinner;
    this.isUsed = isUsed;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Promotion getPromotion() {
    return promotion;
  }

  public void setPromotion(Promotion promotion) {
    this.promotion = promotion;
  }

  public Boolean getWinner() {
    return isWinner;
  }

  public void setWinner(Boolean winner) {
    this.isWinner = winner;
  }

  public Boolean getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Boolean isUsed) {
    this.isUsed = isUsed;
  }
}
