package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import org.aibles.framework.validation.ValidateEmail;
import org.aibles.framework.validation.ValidateGender;
import org.aibles.framework.validation.ValidateLocalDate;
import org.aibles.framework.validation.ValidatePhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEmployeeRequest {

  private static final Logger log = LoggerFactory.getLogger(
      CreateEmployeeRequest.class);

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
  @ValidateLocalDate
  private String dateOfBirth;
  @NotBlank(message = "gender is required")
  @ValidateGender
  private String gender;
  @NotBlank(message = "address is required")
  private String address;
  private String avatar;
  @NotNull(message = "roleIds is required")
  @Size(min = 1, message = "The list of roleIds must contain at least one element.")
  private List<String> roleIds;

  public CreateEmployeeRequest() {
  }

  public CreateEmployeeRequest(String firstName, String middleName, String lastName, String email,
      String phone, String dateOfBirth, String gender, String address, String avatar,
      List<String> roleIds) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.address = address;
    this.avatar = avatar;
    this.roleIds = roleIds;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public List<String> getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(List<String> roleIds) {
    this.roleIds = roleIds;
  }
}
