package org.aibles.business.usecase.impl;

import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.business.dto.request.DeleteEmployeesUseCaseRequest;
import org.aibles.business.usecase.DeleteEmployeesUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteEmployeesUseCaseImpl implements DeleteEmployeesUseCase {

  private static final Logger log = LoggerFactory.getLogger(DeleteEmployeesUseCaseImpl.class);

  private final EmployeeRepository employeeRepository;

  public DeleteEmployeesUseCaseImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public void invoke(DeleteEmployeesUseCaseRequest request) {
    employeeRepository.updateIsDeletedById(request.getEmployeeIds(), true);
  }
}
