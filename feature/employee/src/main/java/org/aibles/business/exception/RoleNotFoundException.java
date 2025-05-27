package org.aibles.business.exception;

public class RoleNotFoundException extends NotFoundException{

  public RoleNotFoundException(String id) {
    setStatus(404);
    setCode("org.aibles.business.exception.RoleNotFoundException");
    addParams("id", id);
  }
}
