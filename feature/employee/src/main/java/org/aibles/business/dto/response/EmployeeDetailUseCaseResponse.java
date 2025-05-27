package org.aibles.business.dto.response;

import java.time.LocalDate;
import java.util.List;
import org.aibles.entity.Employee;

public class EmployeeDetailUseCaseResponse {

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
  private List<RoleResponse> roles;

  public EmployeeDetailUseCaseResponse() {
  }

  public EmployeeDetailUseCaseResponse(String id, String firstName, String middleName, String lastName, String email,
      String phone, LocalDate dateOfBirth, String gender, String address, String avatar,
      List<RoleResponse> roles) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.address = address;
    this.avatar = avatar;
    this.roles = roles;
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

  public List<RoleResponse> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleResponse> roles) {
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public static EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder builder() {
    return new EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder();
  }

  public static class EmployeeDetailUseCaseResponseBuilder {
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
    private List<RoleResponse> roles;

    public EmployeeDetailUseCaseResponseBuilder() {
    }

    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder middleName(String middleName) {
      this.middleName = middleName;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder email(String email) {
      this.email = email;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder dateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder gender(String gender) {
      this.gender = gender;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder address(String address) {
      this.address = address;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder avatar(String avatar) {
      this.avatar = avatar;
      return this;
    }
    public EmployeeDetailUseCaseResponse.EmployeeDetailUseCaseResponseBuilder roleIds(List<RoleResponse> roles) {
      this.roles = roles;
      return this;
    }
    public EmployeeDetailUseCaseResponse build() {
      return new EmployeeDetailUseCaseResponse(id, firstName, middleName, lastName, email, phone,
          dateOfBirth, gender, address, avatar, roles);
    }
  }

  public static EmployeeDetailUseCaseResponse from(Employee employee, List<RoleResponse> roles) {
    EmployeeDetailUseCaseResponse response = new EmployeeDetailUseCaseResponse();
    response.setId(employee.getId());
    response.setFirstName(employee.getFirstName());
    response.setMiddleName(employee.getMiddleName());
    response.setLastName(employee.getLastName());
    response.setEmail(employee.getEmail());
    response.setPhone(employee.getPhone());
    response.setDateOfBirth(employee.getDateOfBirth());
    response.setGender(employee.getGender());
    response.setAddress(employee.getAddress());
    response.setAvatar(employee.getAvatar());
    response.setRoles(roles);
    return response;
  }
}
