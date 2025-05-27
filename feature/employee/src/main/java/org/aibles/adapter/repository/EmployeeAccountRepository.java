package org.aibles.adapter.repository;

public interface EmployeeAccountRepository {

  void save(String accountId, String employeeId);

  String getAccountIdByEmployeeId(String employeeId);
}
