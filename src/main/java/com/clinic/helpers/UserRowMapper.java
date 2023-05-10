package com.clinic.helpers;

import com.clinic.dbTables.User;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class UserRowMapper implements RowMapper<User> {

  @SneakyThrows
  @Override
  public User mapRow(@NotNull ResultSet rs, int rowNum){
    return User.getUserFromResultSet(rs);
  }

}
