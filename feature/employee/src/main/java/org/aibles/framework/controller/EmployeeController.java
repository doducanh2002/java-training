package org.aibles.framework.controller;

import static org.aibles.util.SecurityUtil.getCredential;

import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.business.dto.request.CreateEmployeeUseCaseRequest;
import org.aibles.business.dto.request.DeleteEmployeesUseCaseRequest;
import org.aibles.business.dto.request.PagingRequest;
import org.aibles.business.dto.request.UpdateEmployeeUseCaseRequest;
import org.aibles.business.dto.response.EmployeeDetailUseCaseResponse;
import org.aibles.business.dto.response.PagingResponse;
import org.aibles.business.usecase.*;
import org.aibles.framework.controller.dto.request.CreateEmployeeRequest;
import org.aibles.framework.controller.dto.request.DeleteEmployeesRequest;
import org.aibles.framework.controller.dto.request.PagingReq;
import org.aibles.framework.controller.dto.request.UpdateEmployeeRequest;
import org.aibles.framework.controller.dto.request.UpdateEmployeeSelfRequest;
import org.aibles.framework.controller.dto.response.BaseResponse;
import org.aibles.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final EmployeeDetailUseCase employeeDetailUseCase;
  private final GetListEmployeeUseCase getListEmployeeUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;
  private final UpdateEmployeeSelfUseCase updateEmployeeSelfUseCase;
  private final DeleteEmployeesUseCase deleteEmployeesUseCase;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponse<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
    log.info("(createEmployee)request: {}", request);
    CreateEmployeeUseCaseRequest useCaseRequest = CreateEmployeeUseCaseRequest
        .builder()
        .firstName(request.getFirstName())
        .middleName(request.getMiddleName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .phone(request.getPhone())
        .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
        .gender(request.getGender())
        .address(request.getAddress())
        .avatar(request.getAvatar())
        .roleIds(request.getRoleIds())
        .build();
    createEmployeeUseCase.invoke(useCaseRequest);
    return new BaseResponse<>(HttpStatus.CREATED.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @GetMapping("/self")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<EmployeeDetailUseCaseResponse> getEmployeeDetail() {
    log.info("(getEmployeeDetail)");
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
      employeeDetailUseCase.invokeByAccountId(getCredential())
    );
  }
  @GetMapping("/{employee_id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<EmployeeDetailUseCaseResponse> getEmployeeDetailById(@PathVariable("employee_id") String employeeId) {
    log.info("(getEmployeeDetail)employeeId: {}", employeeId);
    getCredential();
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        employeeDetailUseCase.invokeByEmployeeId(employeeId)
    );
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PagingResponse<EmployeeDetailUseCaseResponse>> getAllEmployees(
      @Valid PagingReq pagingReq,
      @RequestParam(required = false) String[] sortBy) {
    log.info("(getEmployeeDetail)pagingReq: {}, sortBy: {}", pagingReq, sortBy);
    PagingRequest pagingRequest =
        new PagingRequest(pagingReq.getPageNum(), pagingReq.getPageSize(), sortBy);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getListEmployeeUseCase.invoke(pagingRequest)
    );
  }

  @PutMapping("/{employeeId}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> updateEmployee(@PathVariable(value = "employeeId") String employeeId, @RequestBody @Valid UpdateEmployeeRequest request) {
    log.info("(updateEmployee)request: {}", request);
    UpdateEmployeeUseCaseRequest useCaseRequest = UpdateEmployeeUseCaseRequest
            .builder()
            .firstName(request.getFirstName())
            .middleName(request.getMiddleName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .dateOfBirth(request.getDateOfBirth())
            .gender(request.getGender())
            .address(request.getAddress())
            .avatar(request.getAvatar())
            .roles(request.getRoles())
            .build();
    updateEmployeeUseCase.invoke(employeeId, useCaseRequest);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @PutMapping("/self")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> updateEmployee(@RequestBody @Valid UpdateEmployeeSelfRequest request) {
    log.info("(updateEmployee)request: {}", request);

    UpdateEmployeeUseCaseRequest useCaseRequest = UpdateEmployeeUseCaseRequest
            .builder()
            .firstName(request.getFirstName())
            .middleName(request.getMiddleName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .dateOfBirth(request.getDateOfBirth())
            .gender(request.getGender())
            .address(request.getAddress())
            .avatar(request.getAvatar())
            .build();
    updateEmployeeSelfUseCase.invoke(SecurityUtil.getCredential(), useCaseRequest);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> deleteEmployees(@RequestBody @Valid DeleteEmployeesRequest request) {
    log.info("(deleteEmployees)request: {}", request);
    DeleteEmployeesUseCaseRequest useCaseRequest = DeleteEmployeesUseCaseRequest
        .builder()
        .employeeIds(request.getEmployeeIds())
        .build();
    deleteEmployeesUseCase.invoke(useCaseRequest);
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}
