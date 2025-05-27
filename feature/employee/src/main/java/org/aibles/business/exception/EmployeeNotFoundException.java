package org.aibles.business.exception;

public class EmployeeNotFoundException extends NotFoundException {

  public EmployeeNotFoundException() {
    setStatus(404);
    setCode("org.aibles.business.exception.EmployeeNotFoundException");
  }
}
