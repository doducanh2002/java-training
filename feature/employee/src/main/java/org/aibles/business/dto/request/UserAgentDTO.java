package org.aibles.business.dto.request;

import org.aibles.entity.LoginData;


public class UserAgentDTO {
  private String ipAddress;
  private String browserId;
  private String platform;
  private String operatingSystem;
  private String deviceId;

  public UserAgentDTO() {
  }

  public UserAgentDTO(String ipAddress, String browserId, String platform, String operatingSystem,
      String deviceId) {
    this.ipAddress = ipAddress;
    this.browserId = browserId;
    this.platform = platform;
    this.operatingSystem = operatingSystem;
    this.deviceId = deviceId;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getBrowserId() {
    return browserId;
  }

  public void setBrowserId(String browserId) {
    this.browserId = browserId;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public static UserAgentDTOBuilder builder() {
    return new UserAgentDTOBuilder();
  }

  public static LoginData toLoginData(UserAgentDTO userAgentDTO) {
    LoginData loginData = new LoginData();
    loginData.setIpAddress(userAgentDTO.getIpAddress());
    loginData.setBrowserId(userAgentDTO.getBrowserId());
    loginData.setOperatingSystem(userAgentDTO.getOperatingSystem());
    loginData.setDeviceId(userAgentDTO.getDeviceId());
    loginData.setPlatform(userAgentDTO.getPlatform());
    return loginData;
  }

  public static class UserAgentDTOBuilder {
    private String ipAddress;
    private String browserId;
    private String platform;
    private String operatingSystem;
    private String deviceId;

    public UserAgentDTOBuilder() {}

    public UserAgentDTOBuilder ipAddress(String ipAddress) {
      this.ipAddress = ipAddress;
      return this;
    }

    public UserAgentDTOBuilder browserId(String browserId) {
      this.browserId = browserId;
      return this;
    }

    public UserAgentDTOBuilder platform(String platform) {
      this.platform = platform;
      return this;
    }

    public UserAgentDTOBuilder operatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
      return this;
    }

    public UserAgentDTOBuilder deviceId(String deviceId) {
      this.deviceId = deviceId;
      return this;
    }

    public UserAgentDTO build() {
      return new UserAgentDTO(ipAddress, browserId, platform, operatingSystem, deviceId);
    }
  }
}
