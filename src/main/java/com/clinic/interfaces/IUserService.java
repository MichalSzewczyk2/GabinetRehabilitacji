package com.clinic.interfaces;

import com.clinic.dbTables.User;
import com.clinic.exceptions.UserAlreadyExistException;
import com.clinic.helpers.UserDTO;

public interface IUserService {
  User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
}
