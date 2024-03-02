package org.project.monitor.backend.Repository;

import java.util.List;

import org.project.monitor.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  
  List<Order> findByMemberIdOrderByIdDesc(int memberId);
}
