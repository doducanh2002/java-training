package org.aibles.framework.impl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.business.dto.request.PagingRequest;
import org.aibles.business.dto.response.PagingResponse;
import org.aibles.entity.Employee;
import org.aibles.framework.datasource.EmployeeAccountDatabaseEntity;
import org.aibles.framework.datasource.EmployeeDatabaseEntity;
import org.aibles.framework.repository.EmployeeAccountDatabaseEntityRepository;
import org.aibles.framework.repository.EmployeeDatabaseEntityRepository;
import org.aibles.util.EmployeeMapping;
import org.aibles.util.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeDatasourceImpl implements EmployeeRepository {

    private final EmployeeDatabaseEntityRepository employeeDatabaseEntityRepository;
    private final EmployeeAccountDatabaseEntityRepository employeeAccountDatabaseEntityRepository;

    @Override
    public Employee save(Employee employee) {
        EmployeeDatabaseEntity employeeDatabase = EmployeeMapping.mapEntityToDataSource(employee);
        return EmployeeMapping.mapDataSourceToEntity(
                employeeDatabaseEntityRepository.save(employeeDatabase));
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeDatabaseEntityRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Optional<Employee> findByAccountId(String accountId) {
        Optional<EmployeeDatabaseEntity> employeeDatabase = employeeDatabaseEntityRepository.findByAccountId(accountId);
        return employeeDatabase.map(EmployeeMapping::mapDataSourceToEntity);
    }

    @Override
    @Transactional
    public Optional<Employee> findById(String id) {
        Optional<EmployeeDatabaseEntity> employeeDatabase = employeeDatabaseEntityRepository
                .findById(id);
        return employeeDatabase.map(EmployeeMapping::mapDataSourceToEntity);
    }

    @Override
    @Transactional
    public void updateIsFirstLoginByEmail(String email, boolean isFirstLogin) {
        employeeDatabaseEntityRepository.updateIsFirstLoginByEmail(email, isFirstLogin);
    }

    @Override
    public PagingResponse<Employee> findAll(PagingRequest pagingRequest) {
        Pageable pageable = SortUtil.makePageable(
                pagingRequest.getPage(),
                pagingRequest.getSize(),
                pagingRequest.getSort(),
                createFieldList());
        Page<EmployeeDatabaseEntity> employeesPage = employeeDatabaseEntityRepository.findAllByIsDeletedFalse(pageable);
        log.info("(findAll)employeesPage: {} ", employeesPage);
        return EmployeeMapping.convertDatabaseToPagingEntity(employeesPage);
    }

    @Override
    public Employee updateEmployeeSelf(String accountId, Employee employee) {
        EmployeeDatabaseEntity employeeDatabase = EmployeeMapping.mapEntityToDataSource(employee);
        EmployeeAccountDatabaseEntity employeeAccountDatabase = employeeAccountDatabaseEntityRepository.findByAccountId(accountId).orElse(null);
        assert employeeAccountDatabase != null;
        employee.setId(employeeAccountDatabase.getEmployeeId());
        return EmployeeMapping.mapDataSourceToEntity(employeeDatabaseEntityRepository.save(employeeDatabase));
    }

    private Set<String> createFieldList() {
        Set<String> validFields = new HashSet<>();
        Field[] fields = EmployeeDatabaseEntity.class.getDeclaredFields();
        for (Field field : fields) {
            validFields.add(field.getName());
        }
        return validFields;
    }

    @Override
    public void updateIsDeletedById(List<String> ids, boolean isDeleted) {
        employeeDatabaseEntityRepository.updateIsDeletedById(ids, isDeleted);
    }

    @Override
    public boolean existsById(String id) {
        return employeeDatabaseEntityRepository.existsById(id);
    }
}
