package org.aibles.util;

import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.aibles.business.dto.request.UserAgentDTO;
import org.springframework.http.HttpHeaders;

public class UserAgentUtil {

  private UserAgentUtil() {}

  private static final Pattern BROWSER_PATTERN = Pattern.compile("(Firefox|MSIE|Chrome|Safari|Opera|Edg)/([\\d.]+)");
  private static final Pattern OS_PATTERN = Pattern.compile("\\(([^;]+);");
  private static final Pattern DEVICE_PATTERN = Pattern.compile("\\(([^)]+)\\)");
  private static final Pattern MANUFACTURER_PATTERN = Pattern.compile(
      "(Samsung|Huawei|Xiaomi|Sony|LG|Nokia|Motorola|Apple|HTC|Asus|OnePlus|Google|BlackBerry|Lenovo|Microsoft)");

  public static UserAgentDTO createUserAgent(HttpServletRequest request) {
    final String UNDEFINED_VALUE = "Undefined";
    UserAgentDTO dto;
    try {
      UserAgentInfo userAgentInfo = parse(request.getHeader(HttpHeaders.USER_AGENT));
      dto = UserAgentDTO.builder()
          .ipAddress(request.getRemoteAddr())
          .browserId(userAgentInfo.getBrowserName() + userAgentInfo.getBrowserVersion())
          .operatingSystem(userAgentInfo.getOperatingSystem())
          .platform(userAgentInfo.getManufacturer())
          .deviceId(userAgentInfo.getDeviceType())
          .build();
    } catch (Exception e) {
      dto = UserAgentDTO.builder()
          .ipAddress(request.getRemoteAddr())
          .browserId(UNDEFINED_VALUE)
          .operatingSystem(UNDEFINED_VALUE)
          .platform(UNDEFINED_VALUE)
          .deviceId(UNDEFINED_VALUE)
          .build();
    }
    return dto;
  }

  public static UserAgentInfo parse(String userAgentString) {
    UserAgentInfo userAgentInfo = new UserAgentInfo();

    // Extract browser information
    Matcher browserMatcher = BROWSER_PATTERN.matcher(userAgentString);
    if (browserMatcher.find()) {
      userAgentInfo.setBrowserName(browserMatcher.group(1));
      userAgentInfo.setBrowserVersion(browserMatcher.group(2));
    }

    Matcher osMatcher = OS_PATTERN.matcher(userAgentString);
    if (osMatcher.find()) {
      userAgentInfo.setOperatingSystem(osMatcher.group(1));
    }

    Matcher deviceMatcher = DEVICE_PATTERN.matcher(userAgentString);
    if (deviceMatcher.find()) {
      String deviceInfo = deviceMatcher.group(1);
      if (deviceInfo.contains("Mobile") || deviceInfo.contains("Tablet")) {
        userAgentInfo.setDeviceType("Mobile");
      } else {
        userAgentInfo.setDeviceType("Desktop");
      }
    }

    Matcher manufacturerMatcher = MANUFACTURER_PATTERN.matcher(userAgentString);
    if (manufacturerMatcher.find()) {
      userAgentInfo.setManufacturer(manufacturerMatcher.group(1));
    } else {
      userAgentInfo.setManufacturer("Unknown");
    }

    return userAgentInfo;
  }

  @Getter
  @Setter
  public static class UserAgentInfo {

    private String browserName;
    private String browserVersion;
    private String operatingSystem;
    private String deviceType;
    private String manufacturer;
    @Override
    public String toString() {
      return "UserAgentInfo{" +
          "browserName='" + browserName + '\'' +
          ", browserVersion='" + browserVersion + '\'' +
          ", operatingSystem='" + operatingSystem + '\'' +
          ", deviceType='" + deviceType + '\'' +
          '}';
    }
  }
}
