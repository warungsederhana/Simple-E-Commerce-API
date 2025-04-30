package project.ecommerce.api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import project.ecommerce.api.entity.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private String jwtExpirationTime;

  private SecretKey key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(User user) {
    Instant now = Instant.now();
    Instant expirationDate = now.plus(Long.parseLong(jwtExpirationTime), ChronoUnit.MILLIS);

    return Jwts.builder()
        .subject(user.getEmail())
        .claim("id", user.getId())
        .claim("name", user.getName())
        .issuedAt(Date.from(now))
        .expiration(Date.from(expirationDate))
        .signWith(key)
        .compact();
  }

  public String getEmailFromToken(String token) {
    return Jwts.parser()
        .verifyWith(key).build()
        .parseSignedClaims(token).getPayload().getSubject();
  }

  public Claims getAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(key).build()
        .parseSignedClaims(token).getPayload();
  }

  public Long getExpiredAt(String token) {
    return Jwts.parser()
        .verifyWith(key).build()
        .parseSignedClaims(token).getPayload().getExpiration().getTime();
  }

  public boolean validateJwtToken(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (SecurityException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT signature: " + e.getMessage());
    } catch (MalformedJwtException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT token is expired: " + e.getMessage());
    } catch (UnsupportedJwtException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT token is unsupported: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT claim string is empty: " + e.getMessage());
    }
  }
}
