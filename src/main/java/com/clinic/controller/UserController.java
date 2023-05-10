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

  public User getUserByEmail(String email) {
    return userRepository.getByEmail(email);
  }

  public User getUserByUsername(String username) {
    return userRepository.getByUsername(username);
  }

  public User addUser(User user) {
    userRepository.add(user);
    if(userRepository.getByEmail(user.getEmail()) != null){
      return user;
    }
    return null;
  }
}
