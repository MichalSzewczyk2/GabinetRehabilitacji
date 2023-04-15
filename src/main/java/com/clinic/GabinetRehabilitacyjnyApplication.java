package com.clinic;

import com.clinic.dbRepository.UserRepository;
import com.clinic.dbTables.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class GabinetRehabilitacyjnyApplication {
  public static void main(String[] args) {
    SpringApplication.run(GabinetRehabilitacyjnyApplication.class, args);
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

}
