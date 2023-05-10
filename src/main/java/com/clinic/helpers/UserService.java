package com.clinic.helpers;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import com.clinic.exceptions.UserAlreadyExistException;
import com.clinic.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {
  @Autowired
  private UserController controller;

  @Override
  public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
    if (emailExists(userDto.getEmail())) {
      throw new UserAlreadyExistException("There is an account with that email address: "
        + userDto.getEmail());
    }
      User user = new User();
//    user.setName(userDto.getFirstName());
//    user.setSurname(userDto.getLastName());
//    user.setPassword(userDto.getPassword());
//    user.setEmail(userDto.getEmail());
//    user.setRoles(Arrays.asList("ROLE_USER"));

    return user;
    // the rest of the registration operation
  }
  private boolean emailExists(String email) {
    return controller.getUserByEmail(email) != null;
  }
}
