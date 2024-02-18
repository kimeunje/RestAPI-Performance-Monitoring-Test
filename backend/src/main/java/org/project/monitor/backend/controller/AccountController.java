package org.project.monitor.backend.controller;

import java.util.Map;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.project.monitor.backend.Repository.MemberRepository;
import org.project.monitor.backend.entity.Member;
import org.project.monitor.backend.service.JwtService;
import org.project.monitor.backend.service.JwtServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AccountController {

  @Autowired
  MemberRepository memberRepository;

  @PostMapping("account/login")
  public ResponseEntity<Integer> login(@RequestBody Map<String, String> params, HttpServletResponse res) {
    Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

    if (member != null) {
      JwtService jwtService = new JwtServiceImpl();
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

}
