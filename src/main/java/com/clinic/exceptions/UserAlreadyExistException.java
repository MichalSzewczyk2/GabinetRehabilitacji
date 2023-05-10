package com.clinic.exceptions;

import javax.naming.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

  public UserAlreadyExistException(final String message){
    super(message);
  }
}
