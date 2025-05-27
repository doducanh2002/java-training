package org.aibles.business.usecase;

import org.aibles.business.dto.response.EmployeeDetailUseCaseResponse;

public interface EmployeeDetailUseCase {

  EmployeeDetailUseCaseResponse invokeByAccountId(String accountId);

  EmployeeDetailUseCaseResponse invokeByEmployeeId(String employeeId);
}
