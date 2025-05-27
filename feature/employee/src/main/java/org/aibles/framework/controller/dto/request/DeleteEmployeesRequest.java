package org.aibles.framework.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class DeleteEmployeesRequest {

  @NotNull(message = "employeeIds is required")
  @Size(min = 1, message = "employeeIds must contain at least one element")
  private List<String> employeeIds;

  public DeleteEmployeesRequest() {
  }

  public DeleteEmployeesRequest(List<String> employeeIds) {
    this.employeeIds = employeeIds;
  }

  public List<String> getEmployeeIds() {
    return employeeIds;
  }

  public void setEmployeeIds(List<String> employeeIds) {
    this.employeeIds = employeeIds;
  }
}
