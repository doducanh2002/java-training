package org.aibles.business.usecase.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.business.dto.request.PagingRequest;
import org.aibles.business.dto.response.EmployeeDetailUseCaseResponse;
import org.aibles.business.dto.response.PagingResponse;
import org.aibles.business.dto.response.RoleResponse;
import org.aibles.business.usecase.GetListEmployeeUseCase;
import org.aibles.entity.Employee;
import org.aibles.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetListEmployeeUseCaseImpl implements GetListEmployeeUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      GetListEmployeeUseCaseImpl.class);

  private final EmployeeRepository employeeRepository;
  private final RoleRepository roleRepository;

  public GetListEmployeeUseCaseImpl(EmployeeRepository employeeRepository,
      RoleRepository roleRepository) {
    this.employeeRepository = employeeRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public PagingResponse<EmployeeDetailUseCaseResponse> invoke(PagingRequest pagingRequest) {
    PagingResponse<Employee> employeesPage = employeeRepository.findAll(pagingRequest);
    List<EmployeeDetailUseCaseResponse> employeesResponses = employeesPage.getContent().stream().map(employee -> {
      List<Role> roles = roleRepository.getAllRoleByEmployeeId(employee.getId());
      List<RoleResponse> roleResponses = roles.stream()
          .map(role -> new RoleResponse(role.getId(), role.getName()))
          .collect(Collectors.toList());
      return EmployeeDetailUseCaseResponse.from(employee, roleResponses);
    }).collect(Collectors.toList());

    return new PagingResponse<>(
        employeesPage.getPage(),
        employeesPage.getSize(),
        employeesPage.getTotal(),
        employeesResponses
    );
  }
}
