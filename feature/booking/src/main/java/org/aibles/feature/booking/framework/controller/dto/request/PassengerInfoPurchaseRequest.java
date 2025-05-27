package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aibles.feature.booking.framework.validation.ValidateGender;
import org.aibles.feature.booking.framework.validation.ValidateLocalDate;

public class PassengerInfoPurchaseRequest {

  @NotNull(message = "num is required")
  private Integer num;
  @NotBlank(message = "firstName is required")
  private String firstName;
  @NotBlank(message = "middleName is required")
  private String middleName;
  @NotBlank(message = "lastName is required")
  private String lastName;
  @ValidateGender
  @NotBlank(message = "gender is required")
  private String gender;
  @NotBlank(message = "country is required")
  private String country;
  @ValidateLocalDate
  @NotBlank(message = "dob is required")
  private String dob;

  public PassengerInfoPurchaseRequest() {
  }

  public PassengerInfoPurchaseRequest(Integer num, String firstName, String middleName,
      String lastName, String gender, String country, String dob) {
    this.num = num;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.country = country;
    this.dob = dob;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }
}
