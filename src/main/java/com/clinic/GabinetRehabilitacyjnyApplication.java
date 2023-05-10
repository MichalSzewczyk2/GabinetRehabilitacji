package com.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class GabinetRehabilitacyjnyApplication {
  public static void main(String[] args) {
    SpringApplication.run(GabinetRehabilitacyjnyApplication.class, args);
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

}
