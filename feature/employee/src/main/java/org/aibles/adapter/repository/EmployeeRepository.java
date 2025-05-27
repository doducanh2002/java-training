package org.aibles.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.business.dto.request.PagingRequest;
import org.aibles.business.dto.response.PagingResponse;
import org.aibles.entity.Employee;

public interface EmployeeRepository {

  Employee save(Employee employee);

  boolean existsByEmail(String email);

  Optional<Employee> findByAccountId(String accountId);

  Optional<Employee> findById(String id);

  void updateIsFirstLoginByEmail(String email, boolean isFirstLogin);

  PagingResponse<Employee> findAll(PagingRequest pagingRequest);

  Employee updateEmployeeSelf(String accountId, Employee employee);

  void updateIsDeletedById(List<String> id, boolean isDeleted);

  boolean existsById(String id);
}
