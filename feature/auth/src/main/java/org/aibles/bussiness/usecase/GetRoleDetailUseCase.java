package org.aibles.bussiness.usecase;

import org.aibles.entity.Role;

public interface GetRoleDetailUseCase {
    Role invoke(String roleId);
}
