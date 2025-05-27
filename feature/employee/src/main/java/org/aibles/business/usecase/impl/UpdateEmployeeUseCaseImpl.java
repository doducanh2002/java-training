package org.aibles.business.usecase.impl;

import org.aibles.adapter.repository.AccountRoleRepository;
import org.aibles.adapter.repository.EmployeeAccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.business.dto.request.UpdateEmployeeUseCaseRequest;
import org.aibles.business.usecase.UpdateEmployeeUseCase;
import org.aibles.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final EmployeeAccountRepository employeeAccountRepository;
    private static final Logger log = LoggerFactory.getLogger(UpdateEmployeeUseCaseImpl.class);

    public UpdateEmployeeUseCaseImpl(EmployeeRepository employeeRepository, AccountRoleRepository accountRoleRepository, EmployeeAccountRepository employeeAccountRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.employeeAccountRepository = employeeAccountRepository;
    }

    @Override
    public void invoke(String employeeId, UpdateEmployeeUseCaseRequest updateEmployeeUseCaseRequest) {
        Employee employee = new Employee(employeeId,
                updateEmployeeUseCaseRequest.getFirstName(),
                updateEmployeeUseCaseRequest.getMiddleName(),
                updateEmployeeUseCaseRequest.getLastName(),
                updateEmployeeUseCaseRequest.getEmail(),
                updateEmployeeUseCaseRequest.getPhone(),
                updateEmployeeUseCaseRequest.getDateOfBirth(),
                updateEmployeeUseCaseRequest.getGender(),
                updateEmployeeUseCaseRequest.getAddress(),
                updateEmployeeUseCaseRequest.getAvatar());
        employeeRepository.save(employee);
        String accountId = employeeAccountRepository.getAccountIdByEmployeeId(employeeId);
        accountRoleRepository.updateUserRoleByAccountId(accountId, updateEmployeeUseCaseRequest.getRoles());
    }
}
