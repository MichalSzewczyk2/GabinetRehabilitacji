package com.clinic.interfaces;

import com.clinic.dbTables.User;
import com.clinic.exceptions.UserAlreadyExistException;
import com.clinic.dto.UserDTO;

public interface IUserService {
  User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
}
