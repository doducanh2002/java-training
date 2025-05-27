package org.aibles.util;

import java.util.List;
import org.aibles.business.dto.response.PagingResponse;
import org.aibles.entity.Employee;
import org.aibles.framework.datasource.EmployeeDatabaseEntity;
import org.springframework.data.domain.Page;

public class EmployeeMapping {

  public static Employee mapDataSourceToEntity(EmployeeDatabaseEntity employeeDatabaseEntity) {
    if(employeeDatabaseEntity == null) {
      return null;
    }
    Employee employee = new Employee();
    employee.setId(employeeDatabaseEntity.getId());
    employee.setFirstName(employeeDatabaseEntity.getFirstName());
    employee.setMiddleName(employeeDatabaseEntity.getMiddleName());
    employee.setLastName(employeeDatabaseEntity.getLastName());
    employee.setEmail(employeeDatabaseEntity.getEmail());
    employee.setPhone(employeeDatabaseEntity.getPhone());
    employee.setDateOfBirth(employeeDatabaseEntity.getDateOfBirth());
    employee.setGender(employeeDatabaseEntity.getGender());
    employee.setAddress(employeeDatabaseEntity.getAddress());
    employee.setAvatar(employeeDatabaseEntity.getAvatar());
    employee.setFirstLogin(employeeDatabaseEntity.getFirstLogin());
    employee.setDeleted(employeeDatabaseEntity.getDeleted());
    return employee;
  }

  public static EmployeeDatabaseEntity mapEntityToDataSource(Employee employee) {
    EmployeeDatabaseEntity employeeDatabase = new EmployeeDatabaseEntity();
    employeeDatabase.setId(employee.getId());
    employeeDatabase.setFirstName(employee.getFirstName());
    employeeDatabase.setMiddleName(employee.getMiddleName());
    employeeDatabase.setLastName(employee.getLastName());
    employeeDatabase.setEmail(employee.getEmail());
    employeeDatabase.setPhone(employee.getPhone());
    employeeDatabase.setDateOfBirth(employee.getDateOfBirth());
    employeeDatabase.setGender(employee.getGender());
    employeeDatabase.setAddress(employee.getAddress());
    employeeDatabase.setAvatar(employee.getAvatar());
    employeeDatabase.setFirstLogin(employee.getFirstLogin());
    employeeDatabase.setDeleted(employee.getDeleted());
    return employeeDatabase;
  }

  public static PagingResponse<Employee> convertDatabaseToPagingEntity(
      Page<EmployeeDatabaseEntity> databaseEntityPage) {
    List<Employee> employeesList = databaseEntityPage.getContent().stream()
        .map(EmployeeMapping::mapDataSourceToEntity).toList();
    return new PagingResponse<>(databaseEntityPage.getNumber(), databaseEntityPage.getSize(),
        databaseEntityPage.getTotalElements(), employeesList);
  }
}
