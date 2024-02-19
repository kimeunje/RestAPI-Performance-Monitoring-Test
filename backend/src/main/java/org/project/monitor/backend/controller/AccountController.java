package org.project.monitor.backend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.project.monitor.backend.Repository.MemberRepository;
import org.project.monitor.backend.entity.Member;
import org.project.monitor.backend.service.JwtService;
import org.project.monitor.backend.service.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AccountController {

  private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  JwtService jwtService;

  @PostMapping("/api/account/login")
  public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {
    Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

    if (member != null) {
      int id = member.getId();
      String token = jwtService.getToken("id", id);

      Cookie cookie = new Cookie("token", token);
      cookie.setHttpOnly(true);
      cookie.setPath("/");

      res.addCookie(cookie);

      return new ResponseEntity<>(id, HttpStatus.OK);
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/api/account/check")
  public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
    Map<String, Object> response = new HashMap<>();

    Claims claims = jwtService.getClaims(token);

    if (claims != null) {
      int id = Integer.parseInt(claims.get("id").toString());

      Date expirationDate = claims.getExpiration();

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");
      // 원하는 데이터 포맷 지정
      String strNowDate = simpleDateFormat.format(expirationDate);

      logger.info("JWT 만료 시간은?: {}", strNowDate);

      response.put("expirationDate", strNowDate);
      response.put("id", id);
      response.put("message", "Token is valid");
      // return new ResponseEntity<>(id, HttpStatus.OK);
      return ResponseEntity.ok(response);
    }

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
