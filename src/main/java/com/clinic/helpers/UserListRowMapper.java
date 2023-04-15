package com.clinic.helpers;

import com.clinic.dbTables.User;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserListRowMapper implements RowMapper<List<User>> {
  @SneakyThrows
  @Override
  public List<User> mapRow(ResultSet rs, int rowNum) {
    List<User> users = new ArrayList<>();
    users.add(User.getUserFromResultSet(rs));
    while (rs.next()) {
      users.add(User.getUserFromResultSet(rs));
    }
    return users;
  }
}
