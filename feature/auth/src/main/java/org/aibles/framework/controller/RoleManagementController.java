package org.aibles.framework.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.bussiness.dto.GetAllRoleResponse;
import org.aibles.bussiness.usecase.GetAllRoleUseCase;
import org.aibles.bussiness.usecase.GetRoleDetailUseCase;
import org.aibles.entity.Role;
import org.aibles.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleManagementController {
    private final GetAllRoleUseCase getAllRoleUseCase;
    private final GetRoleDetailUseCase getRoleDetailUseCase;

    @GetMapping()
    BaseResponse<List<GetAllRoleResponse>> getAllRole() {
        return new BaseResponse<>(
                HttpStatus.OK.getReasonPhrase(),
                System.currentTimeMillis(),
                getAllRoleUseCase.invoke());
    }

    @GetMapping("/{roleId}")
    BaseResponse<Role> getRoleDetailById(@PathVariable("roleId") String roleId) {
        return new BaseResponse<>(
                HttpStatus.OK.getReasonPhrase(),
                System.currentTimeMillis(),
                getRoleDetailUseCase.invoke(roleId)
        );
    }
}
