package org.aibles.framework.impl;

import org.aibles.adapter.repository.LoginDataRepository;
import org.aibles.entity.LoginData;
import org.aibles.framework.datasource.LoginDataDatabaseEntity;
import org.aibles.framework.repository.LoginDataDatabaseEntityRepository;
import org.aibles.util.LoginDataMapping;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDataDatasourceImpl implements LoginDataRepository {

  private final LoginDataDatabaseEntityRepository loginDataDatabaseEntityRepository;

  public LoginDataDatasourceImpl(
      LoginDataDatabaseEntityRepository loginDataDatabaseEntityRepository) {
    this.loginDataDatabaseEntityRepository = loginDataDatabaseEntityRepository;
  }

  @Override
  public LoginData save(LoginData loginData) {
    LoginDataDatabaseEntity databaseEntity =
        loginDataDatabaseEntityRepository.save(LoginDataMapping.mapToDatasource(loginData));
    return LoginDataMapping.mapToEntity(databaseEntity);
  }
}
