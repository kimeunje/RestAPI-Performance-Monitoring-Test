package org.project.monitor.backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("jwtService")
public class JwtServiceImpl implements JwtService {
  private String secretKey = "asdasdad12c12cd12cd1c12dcddddd!dd12c12ca!!aaaaaaaasdsdzxc";
  private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

  @Override
  public String getToken(String key, Object value) {

    // Date expTime = new Date();
    // expTime.setTime(expTime.getTime() * 1000 * 60 * 5); // 현재 시간에서 5분을 더한 값으로 설정
    Date expTime = new Date(System.currentTimeMillis() + 1000 * 60 * 5); // 현재 시간에서 5분을 더한 값으로 설정

    byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
    Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

    Map<String, Object> headerMap = new HashMap<>();
    headerMap.put("typ", "JWT");
    headerMap.put("alg", "HS256");

    Map<String, Object> map = new HashMap<>();
    map.put(key, value);

    JwtBuilder builder = Jwts.builder().setHeader(headerMap)
        .setClaims(map)
        .setExpiration(expTime)
        .signWith(signKey, SignatureAlgorithm.HS256);

    return builder.compact();
  }

  @Override
  public Claims getClaims(String token) {
    if (token != null && !"".equals(token)) {
      try {
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();

        logger.info("로그인에 성공한 유저: {}", claims.get("id"));

        return claims;
      } catch (ExpiredJwtException e) {
        // 만료
        throw new RuntimeException("Expired token. Please log in again."); // 예시: 런타임 예외를 던져서 처리
      } catch (JwtException e) {
        // 유효하지 않음
      }
    }

    return null;
  }

  @Override
  public boolean isValid(String token) {
    return this.getClaims(token) != null;
  }

  @Override
  public int getId(String token) {
    Claims claims = this.getClaims(token);

    if (claims != null) {
      return Integer.parseInt(claims.get("id").toString());
    }

    return 0;
  }

}
