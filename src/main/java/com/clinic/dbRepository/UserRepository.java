package com.clinic.dbRepository;

import com.clinic.dbTables.User;
import com.clinic.helpers.UserListRowMapper;
import com.clinic.helpers.UserRowMapper;
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

  public User getByEmail(String email) {

    List<User> user =  jdbcTemplate.query(("SELECT * FROM users WHERE mail = '" + email + "';"), new UserRowMapper());
    if(user.isEmpty()){
      return null;
    }
    else{
      return user.get(0);
    }

    //return jdbcTemplate.queryForObject("SELECT * FROM users WHERE mail = ?", new Object[]{email}, (rs, rowNum) -> User.getUserFromResultSet(rs));
  }

  public User getByUsername(String username) {

    List<User> user = jdbcTemplate.query(("SELECT * FROM users WHERE login = '" + username + "';"), new UserRowMapper());
    if(user.isEmpty()){
      return null;
    }
    else{
      return user.get(0);
    }
    //return jdbcTemplate.queryForObject("SELECT * FROM users WHERE login = ?", new Object[]{username}, (rs, rowNum) -> User.getUserFromResultSet(rs));
  }

  public void add(User user) {
    jdbcTemplate.update("INSERT INTO users (name, surname, address, birth_date, phone_number, mail, user_type, login, password) VALUES (?,?,?,?,?,?,?,?,?)",
      user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getStringType(), user.getUsername(), user.getPassword());
  }

  public void update(User user) {
    jdbcTemplate.update("UPDATE users SET name = ?, surname = ?, address = ?, birth_date = ?, phone_number = ?, mail = ?, user_type = ?, login = ?, password = ? WHERE user_id = ?",
      user.getName(), user.getSurname(), user.getAddress(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail(), user.getStringType(), user.getUsername(), user.getPassword(), user.getId());
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
  }


}
