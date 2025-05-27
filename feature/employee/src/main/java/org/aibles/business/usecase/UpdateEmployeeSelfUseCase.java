package org.aibles.business.usecase;

import org.aibles.business.dto.request.UpdateEmployeeUseCaseRequest;

public interface UpdateEmployeeSelfUseCase {
    void invoke(String accountId, UpdateEmployeeUseCaseRequest updateEmployeeUseCaseRequest);
}
