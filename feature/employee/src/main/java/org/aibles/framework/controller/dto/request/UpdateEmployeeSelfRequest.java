package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.aibles.framework.validation.ValidateEmail;
import org.aibles.framework.validation.ValidateGender;
import org.aibles.framework.validation.ValidatePhone;

public class UpdateEmployeeSelfRequest {

  @NotBlank(message = "firstName is required")
  private String firstName;
  @NotBlank(message = "middleName is required")
  private String middleName;
  @NotBlank(message = "lastName is required")
  private String lastName;
  @NotBlank(message = "email is required")
  @ValidateEmail
  private String email;
  @NotBlank(message = "phone is required")
  @ValidatePhone
  private String phone;
  @NotNull(message = "dateOfBirth is required")
  private LocalDate dateOfBirth;
  @NotBlank(message = "gender is required")
  @ValidateGender
  private String gender;
  @NotBlank(message = "address is required")
  private String address;
  @NotBlank(message = "avatar is required")
  private String avatar;

  public UpdateEmployeeSelfRequest(String firstName, String lastName, String middleName,
      String email, String phone, LocalDate dateOfBirth, String gender, String avatar,
      String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.email = email;
    this.phone = phone;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.avatar = avatar;
    this.address = address;
  }

  public UpdateEmployeeSelfRequest() {
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getAddress() {
    return address;
  }

}
