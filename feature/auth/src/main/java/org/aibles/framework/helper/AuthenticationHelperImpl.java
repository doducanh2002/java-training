package org.aibles.framework.helper;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Getter
public class AuthenticationHelperImpl implements AuthenticationHelper {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationHelperImpl.class);
  @Value("${application.access-token.secret-key:1voYhS5i-69dyyotUknx_Jeqg0GFqIaa0wuUvwiu}")
  private String accessTokenSecretKey;
  @Value("${application.refresh-token.secret-key:fa9B46pBhGWnvBO6q6XlpC307vOnx7TECckUvwi}")
  private String refreshTokenSecretKey;
  @Value("${application.access-token.lifetime:3600000}")
  private long accessTokenLifeTime;
  @Value("${application.refresh-token.lifetime:10800000}")
  private long refreshTokenLifeTime;
  private final RestTemplate restTemplate;


  public AuthenticationHelperImpl(ApplicationContext applicationContext) {
    this.restTemplate = applicationContext.getBean("getRestTemplate", RestTemplate.class);
  }

  @Override
  public String getSubjectFromAccessToken(String accessToken) {
    log.info("(getSubjectFromAccessToken)accessToken : {}", accessToken);
    return getClaim(accessToken, Claims::getSubject, accessTokenSecretKey);
  }

  @Override
  public String getSubjectFromRefreshToken(String refreshToken) {
    log.info("(getSubjectFromAccessToken)refreshToken : {}", refreshToken);
    return getClaim(refreshToken, Claims::getSubject, refreshTokenSecretKey);
  }

  @Override
  public boolean isExpiredAccessToken(String token) {
    return isExpiredToken(token, accessTokenSecretKey);
  }

  @Override
  public boolean isExpiredRefreshToken(String token) {
    return isExpiredToken(token, refreshTokenSecretKey);
  }

  @Override
  public String generateAccessToken(String username, String email) {
    log.info("(generateAccessToken) username: {}, email: {}", email, username);
    var claims = new HashMap<String, Object>();
    claims.put("username", username);
    claims.put("email", email);
    return generateToken(username, claims, accessTokenLifeTime, accessTokenSecretKey);
  }

  @Override
  public String generateRefreshToken(String username, String email) {
    log.info("(generateRefreshToken) username: {}, email: {}", email, username);
    var claims = new HashMap<String, Object>();
    claims.put("username", username);
    claims.put("email", email);
    return generateToken(username, claims, refreshTokenLifeTime, refreshTokenSecretKey);
  }

  @Override
  public String convertWildcardRequestUri(String uri) {
    String uuidPattern = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
    String numericPattern = "/\\d+";
    return removeQueryParams(uri.replaceAll(uuidPattern, "*").replaceAll(numericPattern, "/*"));
  }

  public boolean validateOAuth2Token(String token, String jwkSetUri) {
    try {
      // Parse the JWT
      JWT jwt = JWTParser.parse(token);
      SignedJWT signedJWT = (SignedJWT) jwt;

      String jwks = restTemplate.getForObject(jwkSetUri, String.class);
      JWKSet jwkSet = JWKSet.parse(jwks);
      JWK jwk = jwkSet.getKeyByKeyId(signedJWT.getHeader().getKeyID());
      // Verify the token is not expired
      if (new Date().getTime() / 1000 >= signedJWT.getJWTClaimsSet().getExpirationTime().getTime() / 1000) {
        log.info("Token has expired");
        return false;
      }
      // Create a public key from the JWK
      if (jwk instanceof RSAKey rsaKey) {
        RSAPublicKey publicKey = rsaKey.toRSAPublicKey();

        // Create a verifier using the public key
        JWSVerifier verifier = new RSASSAVerifier(publicKey);
        log.info("(validateOAuth2Token)signJWT : {}, publicKey : {}", signedJWT, publicKey);
        // Verify the token
        return signedJWT.verify(verifier);
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  public String getTokenPayload(String token) {
    String[] parts = token.split("\\.");
    Base64.Decoder decoder = Base64.getUrlDecoder();
    return new String(decoder.decode(parts[1]));
  }

  private String generateToken(
      String subject, Map<String, Object> claims, long tokenLifeTime, String jwtSecret) {
    log.info(
        "(generateToken)subject : {}, claims : {}, tokenLifeTime : {}, jwtSecret : {}",
        subject,
        claims,
        tokenLifeTime,
        jwtSecret);
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
        .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
  }

  private <T> T getClaim(String token, Function<Claims, T> claimsResolve, String secretKey) {
    log.info("(getClaim)token : {}, claimsResolve : {}, secretKey : {}", token, claimsResolve,
        secretKey);
    return claimsResolve.apply(getClaims(token, secretKey));
  }

  private Claims getClaims(String token, String secretKey) {
    log.info("(getClaims)token : {}, secretKey : {}", token, secretKey);
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  private boolean isExpiredToken(String token, String secretKey) {
    return getClaim(token, Claims::getExpiration, secretKey).before(new Date());
  }

  private String removeQueryParams(String uri) {
    if (uri.contains("?")) {
      uri = uri.split("\\?")[0];
    }
    return uri;
  }

}
