package org.aibles.bussiness.exception;

import org.aibles.business.exception.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String roleId) {
        setCode("org.aibles.feature.customer.business.exception.RoleNotFoundException");
        addParams("roleId", roleId);
    }


}
