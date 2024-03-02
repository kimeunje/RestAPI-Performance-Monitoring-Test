package org.project.monitor.backend.controller;

import java.util.List;

import org.project.monitor.backend.Repository.CartRepository;
import org.project.monitor.backend.Repository.OrderRepository;
import org.project.monitor.backend.dto.OrderDto;
import org.project.monitor.backend.entity.Order;
import org.project.monitor.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Transactional
public class OrderController {

  @Autowired
  JwtService jwtService;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  CartRepository cartRepository;

  @GetMapping("/api/orders")
  public ResponseEntity getOrder(@CookieValue(value = "token", required = false) String token) {

    if (!jwtService.isValid(token)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    int memberId = jwtService.getId(token);
    
    List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);

    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping("/api/orders")
  public ResponseEntity pushOrder(
      @RequestBody OrderDto dto,
      @CookieValue(value = "token", required = false) String token) {

    if (!jwtService.isValid(token)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    Order newOrder = new Order();

    int memberId = jwtService.getId(token);

    newOrder.setMemberId(memberId);
    newOrder.setName(dto.getName());
    newOrder.setAddress(dto.getAddress());
    newOrder.setPayment(dto.getPayment());
    newOrder.setCardNumber(dto.getCardNumber());
    newOrder.setItems(dto.getItems());

    orderRepository.save(newOrder);

    cartRepository.deleteByMemberId(memberId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
