package org.aibles.business.exception;

public class EmployeeIdNotFoundException extends NotFoundException{

  public EmployeeIdNotFoundException(String id) {
    setStatus(404);
    setCode("org.aibles.business.exception.EmployeeIdNotFoundException");
    addParams("id", id);
  }
}
