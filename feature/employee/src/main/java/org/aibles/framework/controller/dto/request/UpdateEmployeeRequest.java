package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class UpdateEmployeeRequest extends UpdateEmployeeSelfRequest {

  @NotNull(message = "roleIds is required")
  @Size(min = 1, message = "The list of roleIds must contain at least one element.")
  private List<String> roles;

  public UpdateEmployeeRequest(String firstName, String lastName, String middleName, String email,
      String phone, LocalDate dateOfBirth, String gender, String avatar, String address,
      List<String> roles) {
    super(firstName, lastName, middleName, email, phone, dateOfBirth, gender, avatar, address);
    this.roles = roles;
  }

  public UpdateEmployeeRequest() {
    super();
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
