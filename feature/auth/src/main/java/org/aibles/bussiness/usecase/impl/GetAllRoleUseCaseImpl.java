package org.aibles.bussiness.usecase.impl;

import org.aibles.adapter.repository.RoleRepository;
import org.aibles.bussiness.dto.GetAllRoleResponse;
import org.aibles.bussiness.usecase.GetAllRoleUseCase;
import org.aibles.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class GetAllRoleUseCaseImpl implements GetAllRoleUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetAllRoleUseCaseImpl.class);
    private final RoleRepository roleRepository;

    public GetAllRoleUseCaseImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<GetAllRoleResponse> invoke() {
        List<Role> roles = roleRepository.getAllRole();
        return roles.stream().map(GetAllRoleResponse::from).toList();
    }
}
