package org.aibles.framework.configuration;

import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.AccountRoleRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.adapter.repository.EmployeeAccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.adapter.repository.LoginDataRepository;
import org.aibles.adapter.repository.RedisCacheRepository;
import org.aibles.business.usecase.*;
import org.aibles.business.usecase.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfiguration {

  @Bean
  public CreateEmployeeUseCase createEmployeeUseCase(EmployeeRepository employeeRepository,
      AccountRepository accountRepository, EmployeeAccountRepository employeeAccountRepository,
      AccountRoleRepository accountRoleRepository, EmailHelper emailHelper,
      RoleRepository roleRepository) {
    return new CreateEmployeeUseCaseImpl(employeeRepository, accountRepository,
        employeeAccountRepository, accountRoleRepository, emailHelper, roleRepository);
  }

  @Bean
  public LoginEmployeeUseCase loginEmployeeUseCase(AccountRepository accountRepository,
      LoginDataRepository loginDataRepository, EmployeeRepository employeeRepository,
      AuthenticationHelper authenticationHelper,
      RedisCacheRepository redisCacheRepository) {
    return new LoginEmployeeUseCaseImpl(accountRepository, loginDataRepository, employeeRepository,
        authenticationHelper, redisCacheRepository);
  }

  @Bean
  public EmployeeDetailUseCase employeeDetailUseCase(EmployeeRepository employeeRepository,
      AccountRepository accountRepository, RoleRepository roleRepository) {
    return new EmployeeDetailUseCaseImpl(employeeRepository, accountRepository, roleRepository);
  }

  @Bean
  public ChangePasswordFirstTimeLoginUseCase changePasswordFirstTimeLoginUseCase(
      AccountRepository accountRepository, EmployeeRepository employeeRepository,
      RedisCacheRepository redisCacheRepository) {
    return new ChangePasswordFirstTimeLoginUseCaseImpl(accountRepository, employeeRepository,
        redisCacheRepository);
  }

  @Bean
  public GetListEmployeeUseCase getListEmployeeUseCase(EmployeeRepository employeeRepository,
      RoleRepository roleRepository) {
    return new GetListEmployeeUseCaseImpl(employeeRepository, roleRepository);
  }

  @Bean
  public UpdateEmployeeUseCase updateEmployeeUseCase(EmployeeRepository employeeRepository,AccountRoleRepository accountRoleRepository, EmployeeAccountRepository employeeAccountRepository) {
    return new UpdateEmployeeUseCaseImpl(employeeRepository, accountRoleRepository, employeeAccountRepository);
  }

  @Bean
  public UpdateEmployeeSelfUseCase updateEmployeeSelfUseCase (EmployeeRepository employeeRepository, AccountRoleRepository accountRoleRepository) {
    return new UpdateEmployeeUseCaseSelfImpl(employeeRepository, accountRoleRepository);
  }

  @Bean
  public DeleteEmployeesUseCase deleteEmployeesUseCase(EmployeeRepository employeeRepository) {
    return new DeleteEmployeesUseCaseImpl(employeeRepository);
  }
}
