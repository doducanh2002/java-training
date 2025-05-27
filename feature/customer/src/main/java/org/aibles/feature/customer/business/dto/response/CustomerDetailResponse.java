package org.aibles.feature.customer.business.dto.response;

import java.time.LocalDate;
import org.aibles.entity.Customer;

public class CustomerDetailResponse {

  private String id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phone;
  private LocalDate dateOfBirth;
  private String gender;
  private String address;
  private String avatar;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
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

  public static CustomerDetailResponse from(Customer customer) {
    CustomerDetailResponse response = new CustomerDetailResponse();
    response.setId(customer.getId());
    response.setFirstName(customer.getFirstName());
    response.setMiddleName(customer.getMiddleName());
    response.setLastName(customer.getLastName());
    response.setEmail(customer.getEmail());
    response.setPhone(customer.getPhone());
    response.setDateOfBirth(customer.getDateOfBirth());
    response.setGender(customer.getGender());
    response.setAddress(customer.getAddress());
    response.setAvatar(customer.getAvatar());
    return response;
  }
}
