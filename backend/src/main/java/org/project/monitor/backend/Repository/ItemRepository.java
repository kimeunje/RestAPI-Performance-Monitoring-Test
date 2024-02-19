package org.project.monitor.backend.Repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.project.monitor.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
  List<Item> findByIdIn(List<Integer> ids);
}
