package org.aibles.framework.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.EmployeeAccountRepository;
import org.aibles.entity.Employee;
import org.aibles.framework.datasource.EmployeeAccountDatabaseEntity;
import org.aibles.framework.datasource.EmployeeDatabaseEntity;
import org.aibles.framework.repository.EmployeeAccountDatabaseEntityRepository;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeAccountDatasourceImpl implements EmployeeAccountRepository {

  private final EmployeeAccountDatabaseEntityRepository employeeAccountDatabaseEntityRepository;

  @Override
  public void save(String accountId, String employeeId) {
    employeeAccountDatabaseEntityRepository.save(new EmployeeAccountDatabaseEntity(employeeId, accountId));
  }

  @Override
  public String getAccountIdByEmployeeId(String employeeId) {
    return employeeAccountDatabaseEntityRepository.findByEmployeeId(employeeId).get().getEmployeeId();
  }
}
