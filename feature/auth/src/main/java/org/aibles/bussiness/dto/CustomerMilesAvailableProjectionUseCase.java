package org.aibles.bussiness.dto;

import java.util.List;

public class CustomerMilesAvailableProjectionUseCase {

  private String id;
  private Long milesAvailable;

  public CustomerMilesAvailableProjectionUseCase() {
  }

  public CustomerMilesAvailableProjectionUseCase(String id, Long milesAvailable) {
    this.id = id;
    this.milesAvailable = milesAvailable;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getMilesAvailable() {
    return milesAvailable;
  }

  public void setMilesAvailable(Long milesAvailable) {
    this.milesAvailable = milesAvailable;
  }

  public static CustomerMilesAvailableProjectionUseCase.CustomerMilesAvailableProjectionBuilder builder() {
    return new CustomerMilesAvailableProjectionUseCase.CustomerMilesAvailableProjectionBuilder();
  }

  public static class CustomerMilesAvailableProjectionBuilder {

    private String id;
    private Long milesAvailable;

    public CustomerMilesAvailableProjectionBuilder() {
    }

    public CustomerMilesAvailableProjectionUseCase.CustomerMilesAvailableProjectionBuilder id(String id) {
      this.id = id;
      return this;
    }

    public CustomerMilesAvailableProjectionUseCase.CustomerMilesAvailableProjectionBuilder milesAvailable(Long milesAvailable) {
      this.milesAvailable = milesAvailable;
      return this;
    }


    public CustomerMilesAvailableProjectionUseCase build() {
      return new CustomerMilesAvailableProjectionUseCase(id, milesAvailable);
    }
  }
}
