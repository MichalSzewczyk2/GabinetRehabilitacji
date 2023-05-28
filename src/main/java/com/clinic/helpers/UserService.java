package com.clinic.helpers;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.UserType;
import com.clinic.dto.UserDTO;
import com.clinic.exceptions.UserAlreadyExistException;
import com.clinic.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {

  @Autowired
  UserController controller;

  @Override
  public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
    if (emailExists(userDto.getEmail())) {
      throw new UserAlreadyExistException("There is an account with that email address: "
        + userDto.getEmail());
    }
    if (usernameExists(userDto.getUsername())) {
      throw new UserAlreadyExistException("There is an account with that email address: "
        + userDto.getUsername());
    }
    User user = new User();
    user.setName(userDto.getFirstName());
    user.setSurname(userDto.getLastName());
    user.setAddress(userDto.getAddress());
    user.setBirthDate(userDto.getBirthDate());
    user.setPhoneNumber(Integer.parseInt(userDto.getPhoneNumber()));
    user.setEmail(userDto.getEmail());
    user.setUserType(UserType.CLIENT);
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());


    return controller.addUser(user);
  }

  private boolean emailExists(String email) {
    return controller.getUserByEmail(email) != null;
  }

  private boolean usernameExists(String username) {
    return controller.getUserByUsername(username) != null;
  }
}
