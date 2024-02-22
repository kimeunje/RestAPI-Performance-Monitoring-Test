package org.project.monitor.backend.Repository;

import org.project.monitor.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
  
}
