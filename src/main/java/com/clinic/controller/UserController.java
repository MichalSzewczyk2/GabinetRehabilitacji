package com.clinic.controller;

import com.clinic.dbRepository.UserRepository;
import com.clinic.dbTables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/test")
  public int test() {
    return 1;
  }

  @GetMapping("/users")
  public List<User> getAll() {
    return userRepository.getAll();
  }

  @GetMapping("/user1")
  public User getUserById() {
    return userRepository.getById(1);
  }
}
