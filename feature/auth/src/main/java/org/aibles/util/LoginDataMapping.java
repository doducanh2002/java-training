package org.aibles.util;

import org.aibles.entity.LoginData;
import org.aibles.framework.datasource.LoginDataDatabaseEntity;

public class LoginDataMapping {

  private LoginDataMapping() {}

  public static LoginDataDatabaseEntity mapToDatasource(LoginData loginData) {
    LoginDataDatabaseEntity loginDataDatabaseEntity = new LoginDataDatabaseEntity();
    loginDataDatabaseEntity.setLoginTime(loginData.getLoginTime());
    loginDataDatabaseEntity.setAccountId(loginData.getAccountId());
    loginDataDatabaseEntity.setBrowserId(loginData.getBrowserId());
    loginDataDatabaseEntity.setDeviceId(loginData.getDeviceId());
    loginDataDatabaseEntity.setIpAddress(loginData.getIpAddress());
    loginDataDatabaseEntity.setPlatform(loginData.getPlatform());
    loginDataDatabaseEntity.setOperatingSystem(loginData.getOperatingSystem());
    loginDataDatabaseEntity.setSuccess(loginData.getSuccess());
    return loginDataDatabaseEntity;
  }

  public static LoginData mapToEntity(LoginDataDatabaseEntity loginDataDatabaseEntity) {
    LoginData loginData = new LoginData();
    loginData.setId(loginDataDatabaseEntity.getId());
    loginData.setLoginTime(loginDataDatabaseEntity.getLoginTime());
    loginData.setAccountId(loginDataDatabaseEntity.getAccountId());
    loginData.setBrowserId(loginDataDatabaseEntity.getBrowserId());
    loginData.setDeviceId(loginDataDatabaseEntity.getDeviceId());
    loginData.setIpAddress(loginDataDatabaseEntity.getIpAddress());
    loginData.setPlatform(loginDataDatabaseEntity.getPlatform());
    loginData.setOperatingSystem(loginDataDatabaseEntity.getOperatingSystem());
    loginData.setSuccess(loginDataDatabaseEntity.getSuccess());
    loginData.setCreatedAt(loginDataDatabaseEntity.getCreatedAt());
    loginData.setCreatedBy(loginDataDatabaseEntity.getCreatedBy());
    loginData.setLastUpdatedAt(loginDataDatabaseEntity.getLastUpdatedAt());
    loginData.setLastUpdatedBy(loginDataDatabaseEntity.getLastUpdatedBy());
    return loginData;
  }
}
