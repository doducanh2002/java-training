package org.aibles.business.usecase.impl;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.business.dto.response.EmployeeDetailUseCaseResponse;
import org.aibles.business.dto.response.RoleResponse;
import org.aibles.business.exception.EmployeeIdNotFoundException;
import org.aibles.business.exception.EmployeeNotFoundException;
import org.aibles.business.usecase.EmployeeDetailUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDetailUseCaseImpl implements EmployeeDetailUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      EmployeeDetailUseCaseImpl.class);

  private final EmployeeRepository employeeRepository;
  private final AccountRepository accountRepository;
  private final RoleRepository roleRepository;

  public EmployeeDetailUseCaseImpl(EmployeeRepository employeeRepository,
      AccountRepository accountRepository, RoleRepository roleRepository) {
    this.employeeRepository = employeeRepository;
    this.accountRepository = accountRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public EmployeeDetailUseCaseResponse invokeByAccountId(String accountId) {
    var roles = roleRepository.getAllRoleByAccountId(accountId);
    var roleProjection = new ArrayList<RoleResponse>();
    for (var role : roles) {
      var projection = new RoleResponse();
      projection.setId(role.getId());
      projection.setName(role.getName());
      roleProjection.add(projection);
    }
    return employeeRepository.findByAccountId(accountId)
        .map(employee -> EmployeeDetailUseCaseResponse.from(employee, roleProjection))
        .orElseThrow(() -> {
          log.error("(invoke)accountId: {}", accountId);
          throw new EmployeeNotFoundException();
        });
  }

  @Override
  public EmployeeDetailUseCaseResponse invokeByEmployeeId(String employeeId) {
      var employee = employeeRepository
        .findById(employeeId)
        .orElseThrow(() -> {
          log.error("(invoke)employeeId: {}", employeeId);
          throw new EmployeeIdNotFoundException(employeeId);
        });

    var roles = roleRepository.getAllRoleByEmployeeId(employeeId);
    var roleResponses = roles.stream()
        .map(role -> new RoleResponse(role.getId(), role.getName()))
        .collect(Collectors.toList());
    return EmployeeDetailUseCaseResponse.from(employee, roleResponses);
  }
}
