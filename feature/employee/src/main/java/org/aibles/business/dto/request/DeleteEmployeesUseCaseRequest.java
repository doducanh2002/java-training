package org.aibles.business.dto.request;

import java.util.List;

public class DeleteEmployeesUseCaseRequest {

  private List<String> employeeIds;

  public DeleteEmployeesUseCaseRequest() {
  }

  public DeleteEmployeesUseCaseRequest(List<String> employeeIds) {
    this.employeeIds = employeeIds;
  }

  public List<String> getEmployeeIds() {
    return employeeIds;
  }

  public void setEmployeeIds(List<String> employeeIds) {
    this.employeeIds = employeeIds;
  }

  public static DeleteEmployeesUseCaseRequestBuilder builder() {
    return new DeleteEmployeesUseCaseRequestBuilder();
  }

  public static class DeleteEmployeesUseCaseRequestBuilder {

    private List<String> employeeIds;

    public DeleteEmployeesUseCaseRequestBuilder() {
    }

    public DeleteEmployeesUseCaseRequestBuilder employeeIds(List<String> employeeIds) {
      this.employeeIds = employeeIds;
      return this;
    }
    public DeleteEmployeesUseCaseRequest build() {
      return new DeleteEmployeesUseCaseRequest(employeeIds);
    }

  }

}
