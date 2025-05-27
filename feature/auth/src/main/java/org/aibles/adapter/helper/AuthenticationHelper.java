package org.aibles.adapter.helper;

public interface AuthenticationHelper {

  String getSubjectFromAccessToken(String accessToken);

  String getSubjectFromRefreshToken(String refreshToken);

  boolean isExpiredAccessToken(String token);

  boolean isExpiredRefreshToken(String token);

  String generateAccessToken(String username, String email);

  String generateRefreshToken(String username, String email);

  String getAccessTokenSecretKey();

  String getRefreshTokenSecretKey();

  long getAccessTokenLifeTime();

  long getRefreshTokenLifeTime();

  String convertWildcardRequestUri(String uri);

  boolean validateOAuth2Token(String token, String jwkSetUri);

  String getTokenPayload(String token);
}
