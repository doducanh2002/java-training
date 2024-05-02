package org.aibles.privatetraining.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aibles.privatetraining.entity.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    private String generateToken(UserProfile userDetails, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        // Thêm ID người dùng vào thông tin chứa trong token
        claims.put("userId", userDetails.getUserId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public String generateAccessToken(UserProfile userDetails) {
        return generateToken(userDetails, accessTokenExpiration);
    }

    public String generateRefreshToken(UserProfile userDetails) {
        return generateToken(userDetails, refreshTokenExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getAccessTokenExpiration(String token) {
        return accessTokenExpiration;
    }

    public Long getRefreshTokenExpiration(String token) {
        return refreshTokenExpiration;
    }
}
