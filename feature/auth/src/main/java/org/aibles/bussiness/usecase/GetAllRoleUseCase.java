package org.aibles.bussiness.usecase;

import org.aibles.bussiness.dto.GetAllRoleResponse;

import java.util.List;

public interface GetAllRoleUseCase {
    List<GetAllRoleResponse> invoke();
}
