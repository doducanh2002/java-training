package org.aibles.business.usecase;

import org.aibles.business.dto.request.UpdateEmployeeUseCaseRequest;

public interface UpdateEmployeeUseCase {
    void invoke(String employeeId, UpdateEmployeeUseCaseRequest updateEmployeeUseCaseRequest);
}
