package org.aibles.business.usecase.impl;

import org.aibles.adapter.repository.AccountRoleRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.business.dto.request.UpdateEmployeeUseCaseRequest;
import org.aibles.business.usecase.UpdateEmployeeSelfUseCase;
import org.aibles.entity.Employee;
import org.aibles.util.SecurityUtil;

public class UpdateEmployeeUseCaseSelfImpl implements UpdateEmployeeSelfUseCase {
    private final EmployeeRepository employeeRepository;
    private final AccountRoleRepository accountRoleRepository;

    public UpdateEmployeeUseCaseSelfImpl(EmployeeRepository employeeRepository, AccountRoleRepository accountRoleRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public void invoke(String accountId, UpdateEmployeeUseCaseRequest updateEmployeeUseCaseRequest) {
        Employee employee = new Employee(null,
                updateEmployeeUseCaseRequest.getFirstName(),
                updateEmployeeUseCaseRequest.getMiddleName(),
                updateEmployeeUseCaseRequest.getLastName(),
                updateEmployeeUseCaseRequest.getEmail(),
                updateEmployeeUseCaseRequest.getPhone(),
                updateEmployeeUseCaseRequest.getDateOfBirth(),
                updateEmployeeUseCaseRequest.getGender(),
                updateEmployeeUseCaseRequest.getAddress(),
                updateEmployeeUseCaseRequest.getAvatar());
        employeeRepository.updateEmployeeSelf(accountId, employee);
    }
}
