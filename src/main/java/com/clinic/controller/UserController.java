package com.clinic.controller;

import com.clinic.dbRepository.UserRepository;
import com.clinic.dbTables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

  public List<User> getEmployees() {
    List<User> employees = new ArrayList<>();
    List<User> users = userRepository.getAll();
    for(User user : users){
      if(user.getStringType().equals("masseur") || user.getStringType().equals("physiotherapist")){
        employees.add(user);
      }
    }
    return employees;
  }

  public List<User> getUserListByNameAndSurname(String name, String surname){
    List<User> result = new ArrayList<>();
    List<User> users = userRepository.getAll();
    for(User user : users){
      if(user.getName().equals(name) && user.getSurname().equals(surname)){
        result.add(user);
      }
    }
    return result;
  }

  public void updateUser(User user){
    userRepository.update(user);
  }

  public User getUserById(int id) {
    return userRepository.getById(id);
  }

  public User getUserByEmail(String email) {
    return userRepository.getByEmail(email);
  }

  public User getUserByUsername(String username) {
    return userRepository.getByUsername(username);
  }

  public User getUserWithUsername(String username) {
    List<User> users = userRepository.getAll();
    for(User user : users){
      if(user.getUsername().equals(username)){
        return user;
      }
    }
    return null;
  }

  public User addUser(User user) {
    userRepository.add(user);
    if(userRepository.getByEmail(user.getEmail()) != null){
      return user;
    }
    return null;
  }

  public boolean checkUser(User user) {
    User dbUser = userRepository.getById(user.getId());
    return dbUser.equalsUser(user);
  }

  public void deleteUserWithId(int id){
    userRepository.delete(id);
  }
}
