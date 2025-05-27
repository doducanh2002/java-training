package org.aibles.business.exception;

public class EmployeeIsDeletedException extends NotFoundException {

  public EmployeeIsDeletedException() {
    setStatus(404);
    setCode("org.aibles.business.exception.EmployeeIsDeletedException");
  }
}
