package org.aibles.feature.customer.framework.controller.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.aibles.feature.customer.business.dto.response.RefreshAccessTokenUseCaseResponse;

@Getter
@Setter
public class RefreshAccessTokenResponse {
  private String accessToken;
  private String refreshToken;
  private long accessTokenLifeTime;
  private long refreshTokenLifeTime;

  public static RefreshAccessTokenResponse from (RefreshAccessTokenUseCaseResponse refreshAccessTokenUseCase) {
    if (refreshAccessTokenUseCase == null) {
      return null;
    }
    RefreshAccessTokenResponse refreshAccessTokenResponse = new RefreshAccessTokenResponse();
    refreshAccessTokenResponse.setAccessToken(refreshAccessTokenUseCase.getAccessToken());
    refreshAccessTokenResponse.setRefreshToken(refreshAccessTokenUseCase.getRefreshToken());
    refreshAccessTokenResponse.setAccessTokenLifeTime(refreshAccessTokenUseCase.getAccessTokenLifeTime());
    refreshAccessTokenResponse.setRefreshTokenLifeTime(refreshAccessTokenUseCase.getRefreshTokenLifeTime());
    return refreshAccessTokenResponse;
  }
}