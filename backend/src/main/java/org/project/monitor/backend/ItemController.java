package org.project.monitor.backend;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ItemController {

  @GetMapping("/items")
  public List<String> getItems() {
    List<String> items = new ArrayList<>();

    items.add("alpha");
    items.add("beta");
    
    return items;
  }

}
