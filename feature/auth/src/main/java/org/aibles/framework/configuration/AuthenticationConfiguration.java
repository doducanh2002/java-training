package org.aibles.framework.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.ApiManagementRepository;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.PermissionRepository;
import org.aibles.adapter.repository.RolePermissionRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.bussiness.usecase.GetAllRoleUseCase;
import org.aibles.bussiness.usecase.GetRoleDetailUseCase;
import org.aibles.bussiness.usecase.ImportPermissionUseCase;
import org.aibles.bussiness.usecase.impl.GetAllRoleUseCaseImpl;
import org.aibles.bussiness.usecase.impl.GetRoleDetailUseCaseImpl;
import org.aibles.bussiness.usecase.impl.ImportPermissionUsecaseImpl;
import org.aibles.framework.security.AuthenticationFactory;
import org.aibles.framework.security.impl.AuthenticationFactoryImpl;
import org.aibles.framework.security.impl.GoogleAuthenticationProvider;
import org.aibles.framework.security.impl.LocalAuthenticationProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.ApplicationContext;

@Configuration
public class AuthenticationConfiguration {

  @Bean
  public GetAllRoleUseCase getAllRoleUseCase(RoleRepository roleRepository) {
    return new GetAllRoleUseCaseImpl(roleRepository);
  }

  @Bean
  public GetRoleDetailUseCase getRoleDetailUseCase(RoleRepository roleRepository) {
    return new GetRoleDetailUseCaseImpl(roleRepository);
  }

  @Bean
  public ImportPermissionUseCase importExcelUseCase(
      RoleRepository roleRepository,
      PermissionRepository permissionRepository,
      ApiManagementRepository apiManagementRepository,
      RolePermissionRepository rolePermissionRepository,
      ExcelHelper excelHelper
  ) {
    return new ImportPermissionUsecaseImpl(roleRepository, permissionRepository, apiManagementRepository,
        rolePermissionRepository, excelHelper);
  }

  @Bean("getRestTemplate")
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "GOOGLE_AUTHENTICATION_PROVIDER")
  public GoogleAuthenticationProvider googleAuthenticationProvider(AuthenticationHelper authenticationHelper, CustomerRepository customerRepository) {
    return new GoogleAuthenticationProvider(authenticationHelper, objectMapper(), customerRepository);
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return objectMapper;
  }

  @Bean(name = "LOCAL_AUTHENTICATION_PROVIDER")
  public LocalAuthenticationProvider localAuthenticationProvider (AuthenticationHelper authenticationHelper, AccountRepository accountRepository) {
    return new LocalAuthenticationProvider(authenticationHelper, accountRepository);
  }

  @Bean
  public AuthenticationFactory authenticationFactory(ApplicationContext applicationContext) {
    return new AuthenticationFactoryImpl(applicationContext);
  }

}
