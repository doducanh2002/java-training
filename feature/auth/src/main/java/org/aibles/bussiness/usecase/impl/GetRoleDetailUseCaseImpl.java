package org.aibles.bussiness.usecase.impl;

import org.aibles.adapter.repository.RoleRepository;
import org.aibles.bussiness.usecase.GetRoleDetailUseCase;
import org.aibles.entity.Role;

public class GetRoleDetailUseCaseImpl implements GetRoleDetailUseCase {
    private final RoleRepository roleRepository;

    public GetRoleDetailUseCaseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role invoke(String roleId) {
        return roleRepository.getRoleDetail(roleId);
    }
}
