package com.clinic.dbRepository;

import com.clinic.dbTables.User;
import com.clinic.helpers.UserListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<User> getAll() {
    return jdbcTemplate.queryForObject("SELECT * FROM users", new UserListRowMapper());
  }

  public User getById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id must be greater than 0");
    }
    return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?",
      new Object[]{id}, (rs, rowNum) -> User.getUserFromResultSet(rs));
  }

  public void add(User user) {
    jdbcTemplate.update("INSERT INTO users (name, surname, address, birth_date, phone_number, mail, user_type, login, password) VALUES (?,?,?,?,?,?,?,?,?)",
      user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getUserType(), user.getUsername(), user.getPassword());
  }

  public void update(User user) {
    jdbcTemplate.update("UPDATE users SET name = ?, surname = ?, address = ?, birth_date = ?, phone_number = ?, mail = ?, user_type = ?, login = ?, password = ? WHERE user_id = ?",
      user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getUserType(), user.getUsername(), user.getPassword(), user.getId());
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
  }


}
