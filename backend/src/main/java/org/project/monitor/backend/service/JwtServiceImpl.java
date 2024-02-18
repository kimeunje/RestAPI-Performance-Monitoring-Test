package org.project.monitor.backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JwtServiceImpl implements JwtService {
  private String secretKey = "asdasdad12c12cd12cd1c12dcddddd!dd12c12ca!!aaaaaaaasdsdzxc";

  @Override
  public String getToken(String key, Object value) {

    Date expTime = new Date();
    expTime.setTime(expTime.getTime() * 1000 * 60 * 5);
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

}
