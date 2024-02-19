package org.project.monitor.backend.controller;

import java.util.List;

import org.project.monitor.backend.Repository.ItemRepository;
import org.project.monitor.backend.entity.Item;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ItemController {

  @Autowired
  ItemRepository itemRepository;

  @GetMapping("/api/items")
  public List<Item> getItems() {
    List<Item> items = itemRepository.findAll();

    return items;
  }

}
