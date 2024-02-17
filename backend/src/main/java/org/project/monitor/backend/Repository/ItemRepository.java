package org.project.monitor.backend.Repository;

import org.springframework.stereotype.Repository;
import org.project.monitor.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
