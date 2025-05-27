package org.aibles.feature.customer.business.dto.response;

public class ActiveLoginResponse extends LoginResponse {
  private String accessToken;
  private String refreshToken;
  private long accessTokenLifeTime;
  private long refreshTokenLifeTime;

  public ActiveLoginResponse() {
  }

  public ActiveLoginResponse(String accessToken, String refreshToken, long accessTokenLifeTime,
      long refreshTokenLifeTime) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.accessTokenLifeTime = accessTokenLifeTime;
    this.refreshTokenLifeTime = refreshTokenLifeTime;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public long getAccessTokenLifeTime() {
    return accessTokenLifeTime;
  }

  public void setAccessTokenLifeTime(long accessTokenLifeTime) {
    this.accessTokenLifeTime = accessTokenLifeTime;
  }

  public long getRefreshTokenLifeTime() {
    return refreshTokenLifeTime;
  }

  public void setRefreshTokenLifeTime(long refreshTokenLifeTime) {
    this.refreshTokenLifeTime = refreshTokenLifeTime;
  }
}
