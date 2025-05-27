package org.aibles.business.dto.request;

import java.time.LocalDate;
import java.util.List;
import org.aibles.framework.constant.Gender;

public class CreateEmployeeUseCaseRequest {

  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phone;
  private LocalDate dateOfBirth;
  private String gender;
  private String address;
  private String avatar;
  private List<String> roleIds;

  public CreateEmployeeUseCaseRequest() {
  }

  public CreateEmployeeUseCaseRequest(String firstName, String middleName, String lastName, String email,
      String phone, LocalDate dateOfBirth, String gender, String address, String avatar,
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

  public List<String> getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(List<String> roleIds) {
    this.roleIds = roleIds;
  }

  public static CreateEmployeeUseCaseRequestBuilder builder() {
    return new CreateEmployeeUseCaseRequestBuilder();
  }

  public static class CreateEmployeeUseCaseRequestBuilder {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String avatar;
    private List<String> roleIds;

    public CreateEmployeeUseCaseRequestBuilder() {
    }

    public CreateEmployeeUseCaseRequestBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder middleName(String middleName) {
      this.middleName = middleName;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder email(String email) {
      this.email = email;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder dateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder gender(String gender) {
      this.gender = gender;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder address(String address) {
      this.address = address;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder avatar(String avatar) {
      this.avatar = avatar;
      return this;
    }
    public CreateEmployeeUseCaseRequestBuilder roleIds(List<String> roleIds) {
      this.roleIds = roleIds;
      return this;
    }
    public CreateEmployeeUseCaseRequest build() {
      return new CreateEmployeeUseCaseRequest(firstName, middleName, lastName, email, phone,
          dateOfBirth, gender, address, avatar, roleIds);
    }
  }
}
